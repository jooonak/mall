package com.worksout.common;

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

	public String sendFile(String dirname, String filename, InputStream is) throws IOException {
		long startTime = System.currentTimeMillis();
		log.info("FILE SEND START : " + startTime);

		String dirPath = dirname + "/" + filename;

		try (BufferedInputStream bis = new BufferedInputStream(is)) {
			log.info("MKDIR? : " + sf.getSession().mkdir(dirname));
			sf.getSession().append( bis, dirPath );
			long endTime = System.currentTimeMillis();
			log.info("FILE SEND END : " + endTime);
			log.info("EXECUTION TIME : " + (endTime - startTime));
			return dirPath;
		} catch (SocketException e) {
			return null;
		}
	}

}
