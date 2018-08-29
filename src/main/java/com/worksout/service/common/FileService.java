package com.worksout.service.common;

import com.worksout.common.FTPWorker;
import com.worksout.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@PropertySource("classpath:/config/files.properties")
public class FileService {

	@Value("${ftp.image.path}")
	private String imgPath;

	@Value("${ftp.excel.path}")
	private String excelPath;

	@Autowired
	private FTPWorker ftpWorker;

	public String sendImage(MultipartFile file) throws IOException {

		String dirPath = imgPath + FileUtils.makeDirectoryName();
		String filename = FileUtils.makeFilename() + "-" + file.getOriginalFilename();

		return ftpWorker.sendFile(dirPath, filename, file.getInputStream());
	}
}
