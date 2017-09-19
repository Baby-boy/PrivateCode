package com.glanway.hr.dms.utils;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 邮件发送工具.
 *
 * @author FUQIHAO
 * @version 1.0.0
 * @dateTime 2017年6月26日 上午11:24:38
 */
public class MailSender {

	@Value("${mail.username}")
	public String sendFrom;

	@Value("${mail.emailAddress}")
	private String emailAddress;

	@Value("${mail.ccAddress}")
	private String ccAddress;

	@Autowired
	private JavaMailSenderImpl mailSender;

	public void sendEmail(String subject, String content, String attachmentFilename, File file) throws EmailException {

		if (StringUtils.isNotEmpty(emailAddress)) {
			String[] emailAddresses = StringUtils.split(emailAddress, ",");

			MimeMessage message = mailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setTo(emailAddresses);// 收件人邮箱地址
				if (StringUtils.isNotEmpty(ccAddress)) {
					String[] ccAddresses = StringUtils.split(ccAddress, ",");
					helper.setCc(ccAddresses);
				}
				helper.setFrom(sendFrom);// 发件人
				helper.setSubject(subject);// 主题
				helper.setText(content);// 邮件正文
				// 当附件信息不为空或者存在附件时才发送附件
				if (StringUtils.isNotEmpty(attachmentFilename) && null != file) {
					helper.addAttachment(attachmentFilename, file);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			mailSender.send(message);
		}

	}
}
