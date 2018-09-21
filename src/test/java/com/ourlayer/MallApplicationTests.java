package com.ourlayer;

import com.ourlayer.common.FTPWorker;
import lombok.extern.java.Log;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Value("${ftp.image.path}")
	private String imgPath;

	@Autowired
	private FTPWorker ftpWorker;

	public void FTPTest () throws IOException{
		ftpWorker.sendFile(imgPath, "testFile.jpg", new FileInputStream("C:\\Users\\Administrator\\Desktop\\image\\000001-gray.jpg"));
	}
}
