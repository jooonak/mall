package com.ourlayer.controller.common;

import com.ourlayer.security.dto.SecurityMember;
import com.ourlayer.service.common.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/goods-image")
	public String uploadGoodsImage(MultipartFile file) {
		try {
			SecurityMember member = (SecurityMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return fileService.sendGoodsImage(file, member.getUsername());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
