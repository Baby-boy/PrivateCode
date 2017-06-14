package com.glanway.hr.app.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailSender {
	@Value("${mail.isDebug}")
	private String isDebug;
	
	@Value("${mail.username}")
	private String userName;
	
	@Value("${mail.password}")
	private String password;
	
	@Value("${mail.host}")
	private String hostName;
	
	@Value("${mail.sendFrom}")
	private String sendFrom;
	
	@Value("${mail.charset}")
	private String charset;
	
	public void sendEmail(String subject, String emailAddress, String Content) throws EmailException{
		
			HtmlEmail email = new HtmlEmail();
			email.setDebug(Boolean.parseBoolean(isDebug));  
			email.setHostName(hostName);//smtp.sina.com.cn
			email.setAuthenticator(new DefaultAuthenticator(userName, password)); //邮箱验证
			email.addTo(emailAddress);      //接收的邮箱
			email.setFrom(sendFrom);    //发送的邮箱
			email.setCharset(charset);        //编码
			email.setSubject(subject);     //标题
			email.setHtmlMsg(Content);		//邮件内容
			
			email.send();
	}
}
