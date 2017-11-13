package com.jhonelee.jfdf.image.service;

import com.jhonelee.jfdf.image.entity.Image;

public interface ImageService {
	
	public Image saveFile(String fileName, byte[] byteArray);
	
	public Image loadFile(String fileName);

}
