package com.glanway.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 邮件发送工具.
 *
 * @author fuqihao
 * @version 1.0
 * @date 2018年5月16日
 */
public class MailSender {

	/** 发送地址 */
	private String sender;
	/** 接收地址 */
	private String[] to;
	/** 抄送地址 */
	private String[] cc;

	@Autowired
	private JavaMailSenderImpl mailSender;

	public void sendEmail(final String subject, final String content) {
		sendEmail(subject, content, null);
	}

	public void sendEmail(final String subject, final String content, final String fileName, final File file) {
		sendEmail(subject, content, null, null, fileName, file);
	}

	public void sendEmail(final String subject, final String content, final String[] to) {
		sendEmail(subject, content, null, to);
	}

	public void sendEmail(final String subject, final String content, final String[] to, final String fileName,
			final File file) {
		sendEmail(subject, content, null, to, fileName, file);
	}

	public void sendEmail(final String subject, final String content, final String sender, final String[] to) {
		sendEmail(subject, content, sender, to, null, null);
	}

	/**
	 * 发送邮件.
	 *
	 * @param subject
	 * @param content
	 * @param sender
	 * @param to
	 * @param fileName
	 * @param file
	 * @author fuqihao
	 * @date 2018年5月16日
	 */
	public void sendEmail(final String subject, final String content, final String sender, final String[] to,
			final String fileName, final File file) {
		try {
			if (StringUtils.isEmpty(sender) || StringUtils.isEmpty(this.sender)) {
				throw new IllegalAccessException("The send address is empty.");
			}
			if (null == to || 0 >= to.length || null == this.to || 0 >= this.to.length) {
				throw new IllegalAccessException("The accept address is empty.");
			}

			final MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			// 发件地址
			if (StringUtils.isNotEmpty(sender)) {
				helper.setFrom(sender);
			} else {
				helper.setFrom(this.sender);
			}

			// 收件地址
			if (null != to && 0 < to.length) {
				helper.setTo(to);
			} else {
				helper.setTo(this.to);
			}

			// 主题
			helper.setSubject(subject);
			// 邮件正文
			helper.setText(content);
			// 抄送地址
			if (null != cc && 0 < cc.length) {
				helper.setCc(cc);
			}

			// 当附件信息不为空或者存在附件时才发送附件
			if (StringUtils.isNotEmpty(fileName) && null != file) {
				helper.addAttachment(fileName, file);
			}

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendSimpleEmail(final String subject, final String content) {
		sendSimpleEmail(subject, content, null, null);
	}

	public void sendSimpleEmail(final String subject, final String content, final String[] to) {
		sendSimpleEmail(subject, content, null, to);
	}

	/**
	 * 发送简单邮件.
	 *
	 * @param subject
	 * @param content
	 * @param sender
	 * @param to
	 * @author fuqihao
	 * @date 2018年5月16日
	 */
	public void sendSimpleEmail(final String subject, final String content, final String sender, final String[] to) {
		try {
			if (StringUtils.isEmpty(sender) || StringUtils.isEmpty(this.sender)) {
				throw new IllegalAccessException("The send address is empty.");
			}
			if (null == to || 0 >= to.length || null == this.to || 0 >= this.to.length) {
				throw new IllegalAccessException("The accept address is empty.");
			}

			SimpleMailMessage message = new SimpleMailMessage();
			// 发件地址
			if (StringUtils.isNotEmpty(sender)) {
				message.setFrom(sender);
			} else {
				message.setFrom(this.sender);
			}

			// 收件地址
			if (null != to && 0 < to.length) {
				message.setTo(to);
			} else {
				message.setTo(this.to);
			}

			// 主题
			message.setSubject(subject);
			// 邮件正文
			message.setText(content);
			// 抄送地址
			if (null != cc && 0 < cc.length) {
				message.setCc(cc);
			}

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

}
