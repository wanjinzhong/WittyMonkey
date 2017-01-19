package com.wittymonkey.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {
	public static void sendValidateCode(String address){
		final Properties props = new Properties();
		try {
			props.load(ClassLoader.class.getResourceAsStream("/mail.properties"));
			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(props.getProperty("address"),props.getProperty("name")));
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(
					"1051750377@qq.com"));
			msg.setSubject("测试邮件");
			msg.setContent("<h1>这是一条测试邮件,本次验证码：" + getValidateCode() + "</h1>","text/html;charset=utf-8");
			Transport transport = session.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(props.getProperty("smtp"), props.getProperty("address"), props.getProperty("password"));
			// 发送
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			/*HtmlEmail email = new HtmlEmail();
			email.setHostName(props.getProperty("smtp"));
			email.setCharset(props.getProperty("encoding"));
			email.addTo(address);
			email.setFrom(props.getProperty("address"), props.getProperty("name"));
			email.setAuthentication(props.getProperty("address"), props.getProperty("password"));
			email.setSubject("测试邮件");
			email.setMsg("这是一条测试邮件,本次验证码：" + getValidateCode());
			email.send();*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		System.out.println("邮件发送成功");
	}
	private static String getValidateCode(){
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < 6; i ++){
			buffer.append((int)(Math.random()*10));
		}
		return buffer.toString();
	}
}
