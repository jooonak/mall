package com.worksout.controller.common;

import com.worksout.service.common.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/goods-image")
	public Map<String, String> uploadGoodsImage(MultipartFile file) {
		System.out.println(file);
		HashMap<String, String> result = new HashMap<>();

		try {
			result.put("filePath", fileService.sendImage(file));
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
