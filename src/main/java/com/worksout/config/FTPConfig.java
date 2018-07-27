package com.worksout.config;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

@Configuration
@PropertySource("classpath:/config/files.properties")
public class FTPConfig {

	@Value("${ftp.host}")
	private String ftpHost;

	@Value("${ftp.port}")
	private Integer ftpPort;

	@Value("${ftp.id}")
	private String ftpId;

	@Value("${ftp.pwd}")
	private String ftpPwd;

	@Bean
	public SessionFactory<FTPFile> sf() {
		DefaultFtpSessionFactory sf = new DefaultFtpSessionFactory();
		sf.setHost(ftpHost);
		sf.setPort(ftpPort);
		sf.setUsername(ftpId);
		sf.setPassword(ftpPwd);
		sf.setControlEncoding("euc-kr");                                // 한글파일 업/다운로드를 위한 인코딩
		sf.setClientMode(FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE); // reference -> http://egloos.zum.com/wharak/v/1208694
		sf.setBufferSize(10000);
		return new CachingSessionFactory<>(sf);
	}

}
