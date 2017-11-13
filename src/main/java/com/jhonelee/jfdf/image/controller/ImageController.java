package com.jhonelee.jfdf.image.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.image.entity.Image;
import com.jhonelee.jfdf.image.service.ImageService;

@Controller
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@ResponseBody
	@RequestMapping(value = "/image/*", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFile(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		String fileName = StringUtils.substringAfterLast(uri, "/");
		Image fileObject = this.imageService.loadFile(fileName);
		
		return ResponseEntity
				.ok()
				.contentLength(fileObject.getContent().length)
				.contentType(MediaType.IMAGE_JPEG)
				.body(fileObject.getContent());
	}

}
