package com.wittymonkey.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class SendEmail {
    public static String sendValidateCode(String address) {
        final Properties props = new Properties();
        String code = null;
        try {
            code = getValidateCode();
            props.load(SendEmail.class.getResourceAsStream("/mail.properties"));
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(props.getProperty("address"), props.getProperty("name")));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                    address));
            msg.setSubject("注册验证");
            msg.setContent("<h2>欢迎注册俏皮猴酒店管理系统,本次验证码：" + code + "</h2>", "text/html;charset=utf-8");
            Transport transport = session.getTransport("smtp");
            // smtp验证，用来发邮件的邮箱用户名密码
            transport.connect(props.getProperty("smtp"), props.getProperty("address"), props.getProperty("password"));
            // 发送
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            code = null;
        } catch (IOException e) {
            e.printStackTrace();
            code = null;
        } catch (AddressException e) {
            e.printStackTrace();
            code = null;
        } catch (MessagingException e) {
            e.printStackTrace();
            code = null;
        }
        return code;
    }

    private static String getValidateCode() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            buffer.append((int) (Math.random() * 10));
        }
        return buffer.toString();
    }
}
