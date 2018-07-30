package com.worksout.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Component
public class Mailer {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendToken(String to, String subject, String token) {

		MimeMessagePreparator preparator = ( (mimeMessage) -> {
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.setFrom(new InternetAddress("customer@mall.com"));
			mimeMessage.setSubject(subject);
			mimeMessage.setText(getHTML(token), "UTF-8", "html");
			mimeMessage.setSentDate(new Date());
		});

		try {
			mailSender.send(preparator);
			return true;
		} catch (MailException e) {
			e.printStackTrace();
			return false;
		}

	}

	private String getHTML(String token) {
		URL url;
		HttpURLConnection con;
		BufferedReader br;
		StringBuilder html = new StringBuilder();
		try {
			url = new URL("https://www.worksout-b2b.co.kr/mail/password-token?token=" + token);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			br= new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String line;
			while ( (line = br.readLine()) != null ) {
				html.append(line).append("\n");
			}
			br.close();
			return html.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
