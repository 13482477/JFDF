package com.jhonelee.jfdf.fileobject.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.fileobject.entity.FileObject;
import com.jhonelee.jfdf.fileobject.entity.FileType;
import com.jhonelee.jfdf.fileobject.repository.FileObjectRepository;
import com.jhonelee.jfdf.sequence.service.SequenceService;

@Service
public class DbFileObjectService implements FileObjectService {
	
	@Autowired
	private FileObjectRepository fileObjectRepository;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public FileObject saveFile(String fileName, Byte[] fileBytes) {
		FileObject fileObject = new FileObject();
		fileObject.setOriginalFileName(fileName);
		fileObject.setFileType(this.resolveFileType(fileName));
		
		
		
		return null;
	}
	
	private FileType resolveFileType(String originalFileName) {
		String suffix = StringUtils.substringAfterLast(originalFileName, ".");
		if (StringUtils.isBlank(suffix)) {
			return null;
		}
		
		return FileType.valueOf(suffix.toUpperCase());
	}
	

	@Override
	public Byte[] loadFile(String fileName) {
		return null;
	}

}
