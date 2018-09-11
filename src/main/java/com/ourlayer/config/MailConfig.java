package com.ourlayer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:/config/mail.properties")
public class MailConfig {

	@Value("${mail.host}")
	private String mailHost;

	@Value("${mail.port}")
	private Integer mailPort;

	@Value("${mail.id}")
	private String mailId;

	@Value("${mail.pwd}")
	private String mailPwd;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setProtocol("smtp");
		mailSender.setHost(mailHost);
		mailSender.setPort(mailPort);
		mailSender.setUsername(mailId);
		mailSender.setPassword(mailPwd);
		mailSender.setDefaultEncoding("UTF-8");
		mailSender.setJavaMailProperties(mailProperties());
		return mailSender;
	}

	private Properties mailProperties() {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.debug", "true");
		return props;
	}

}
