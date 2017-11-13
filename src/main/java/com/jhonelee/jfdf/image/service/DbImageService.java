package com.jhonelee.jfdf.image.service;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhonelee.jfdf.image.entity.Image;
import com.jhonelee.jfdf.image.entity.ImageType;
import com.jhonelee.jfdf.image.repository.ImageRepository;
import com.jhonelee.jfdf.image.utils.MD5Utils;
import com.jhonelee.jfdf.sequence.service.SequenceService;

@Configurable
public class DbImageService implements ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	@Transactional
	public Image saveFile(String originalFileName, byte[] byteArray) {
		Image image = new Image();
		image.setOriginalFileName(originalFileName);
		image.setFileType(this.resolveFileType(originalFileName));
		image.setFileName(this.generateNewFileName(originalFileName));
		image.setMd5(MD5Utils.getMd5FromBytes(byteArray));
		image.setContent(byteArray);
		return this.imageRepository.save(image);
	}
	
	private ImageType resolveFileType(String originalFileName) {
		String suffix = StringUtils.substringAfterLast(originalFileName, ".");
		if (StringUtils.isBlank(suffix)) {
			return null;
		}
		
		return ImageType.valueOf(suffix.toLowerCase());
	}
	
	private String generateNewFileName(String originalFileName) {
		String suffix = StringUtils.substringAfterLast(originalFileName, ".");
		String sequence = this.sequenceService.getNextValue("GLOBAL").toString();
		return StringUtils.isBlank(suffix) ? sequence : sequence + "." + suffix;
	}
	

	@Override
	public Image loadFile(String fileName) {
		return this.imageRepository.getByFileName(fileName);
	}
	
	@Configuration
	@ConditionalOnMissingBean(name = "imageService")
	public static class DbFileObjectServiceConfig {
		
		@Bean
		public ImageService imageService() {
			return new DbImageService();
		}
		
	}

}
