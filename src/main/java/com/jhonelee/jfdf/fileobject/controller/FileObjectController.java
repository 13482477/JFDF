package com.jhonelee.jfdf.fileobject.controller;

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

import com.jhonelee.jfdf.fileobject.entity.FileObject;
import com.jhonelee.jfdf.fileobject.service.FileObjectService;

@Controller
public class FileObjectController {
	
	@Autowired
	private FileObjectService fileObjectService;
	
	@ResponseBody
	@RequestMapping(value = "/file/*", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFile(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		String fileName = StringUtils.substringAfterLast(uri, "/");
		FileObject fileObject = this.fileObjectService.loadFile(fileName);
		
		return ResponseEntity
				.ok()
				.contentLength(fileObject.getContent().length)
				.contentType(MediaType.IMAGE_JPEG)
				.body(fileObject.getContent());
	}

}
