package com.jhonelee.jfdf.fileobject.service;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhonelee.jfdf.fileobject.entity.FileObject;
import com.jhonelee.jfdf.fileobject.entity.FileType;
import com.jhonelee.jfdf.fileobject.repository.FileObjectRepository;
import com.jhonelee.jfdf.fileobject.utils.MD5Utils;
import com.jhonelee.jfdf.sequence.service.SequenceService;

@Configurable
public class DbFileObjectService implements FileObjectService {
	
	@Autowired
	private FileObjectRepository fileObjectRepository;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	@Transactional
	public FileObject saveFile(String originalFileName, byte[] byteArray) {
		FileObject fileObject = new FileObject();
		fileObject.setOriginalFileName(originalFileName);
		fileObject.setFileType(this.resolveFileType(originalFileName));
		fileObject.setFileName(this.generateNewFileName(originalFileName));
		fileObject.setMd5(MD5Utils.getMd5FromBytes(byteArray));
		fileObject.setContent(byteArray);
		return this.fileObjectRepository.save(fileObject);
	}
	
	private FileType resolveFileType(String originalFileName) {
		String suffix = StringUtils.substringAfterLast(originalFileName, ".");
		if (StringUtils.isBlank(suffix)) {
			return null;
		}
		
		return FileType.valueOf(suffix.toLowerCase());
	}
	
	private String generateNewFileName(String originalFileName) {
		String suffix = StringUtils.substringAfterLast(originalFileName, ".");
		String sequence = this.sequenceService.getNextValue("GLOBAL").toString();
		return StringUtils.isBlank(suffix) ? sequence : sequence + "." + suffix;
	}
	

	@Override
	public FileObject loadFile(String fileName) {
		return this.fileObjectRepository.getByFileName(fileName);
	}
	
	@Configuration
	@ConditionalOnMissingBean(name = "fileObjectService")
	public static class DbFileObjectServiceConfig {
		
		@Bean
		public FileObjectService fileObjectService() {
			return new DbFileObjectService();
		}
		
	}

}
