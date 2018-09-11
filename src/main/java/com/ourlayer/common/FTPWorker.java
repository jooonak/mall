package com.ourlayer.common;

import lombok.extern.java.Log;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

@Log
@Component
public class FTPWorker {

	@Autowired
	SessionFactory<FTPFile> sf;

	public String sendFile(String dirPath, String filename, InputStream is) throws IOException {
		String fullPath = dirPath + "/" + filename;

		try (BufferedInputStream bis = new BufferedInputStream(is)) {
			String[] tempArr = dirPath.split("/");
			StringBuilder temp = new StringBuilder();
			for (int i = 0; i < dirPath.split("/").length; i++) {
				temp.append("/").append(tempArr[i]);
				log.info("MKDIR? : " + sf.getSession().mkdir(temp.toString()));
			}
			sf.getSession().append( bis, fullPath );
			return fullPath;
		} catch (SocketException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void removeFile(String filePath) throws IOException {
		sf.getSession().remove(filePath);
	}

}
