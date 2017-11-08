package com.jhonelee.jfdf.fileobject.service;

import javax.transaction.Transactional;

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
	@Transactional
	public FileObject saveFile(String fileName, Byte[] fileBytes) {
		FileObject fileObject = new FileObject();
		fileObject.setOriginalFileName(fileName);
		fileObject.setFileType(this.resolveFileType(fileName));
		fileObject.setContent(fileBytes);
		fileObject.setFileName(this.generateNewFileName(fileName));
		this.fileObjectRepository.save(fileObject);
		return this.fileObjectRepository.save(fileObject);
	}
	
	private FileType resolveFileType(String originalFileName) {
		String suffix = StringUtils.substringAfterLast(originalFileName, ".");
		if (StringUtils.isBlank(suffix)) {
			return null;
		}
		
		return FileType.valueOf(suffix.toUpperCase());
	}
	
	private String generateNewFileName(String originalFileName) {
		String suffix = StringUtils.substringAfterLast(originalFileName, ".");
		String sequence = this.sequenceService.getNextValue("GLOBAL").toString();
		return StringUtils.isBlank(suffix) ? sequence : sequence + "." + suffix;
	}
	

	@Override
	public Byte[] loadFile(String fileName) {
		return null;
	}

}
