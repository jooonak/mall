package com.ourlayer.service.common;

import com.ourlayer.common.FTPWorker;
import com.ourlayer.dto.goods.GoodsImg;
import com.ourlayer.mapper.goods.GoodsMapper;
import com.ourlayer.service.goods.GoodsService;
import com.ourlayer.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@Service
@PropertySource("classpath:/config/files.properties")
public class FileService {

	@Value("${ftp.image.path}")
	private String imgPath;

	@Value("${ftp.excel.path}")
	private String excelPath;

	@Autowired
	private FTPWorker ftpWorker;

	@Autowired
	private GoodsMapper goodsMapper;

	public String sendGoodsImage(MultipartFile file, String username) throws IOException {

		String dirPath = imgPath + "goods/" + FileUtils.makeDirectoryName();
		String filename = FileUtils.makeFilename() + "-" + file.getOriginalFilename();

		String resultPath = ftpWorker.sendFile(dirPath, filename, file.getInputStream());
		
		GoodsImg goodsImg = new GoodsImg();
		goodsImg.setFilePath(resultPath);
		goodsImg.setGoodsNo(FileUtils.extractGoodsNoFromFilename(file.getOriginalFilename()));
		goodsImg.setOrd(FileUtils.extractOrdFromFilename(file.getOriginalFilename()));
		goodsImg.setRegr(username);
		
		try {
			goodsMapper.insertGoodsImage(goodsImg);
		} catch (Exception e) {
			ftpWorker.removeFile(resultPath);
		}

		return resultPath;
	}
}
