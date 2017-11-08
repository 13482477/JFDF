package com.jhonelee.jfdf.fileobject.service;

import com.jhonelee.jfdf.fileobject.entity.FileObject;

public interface FileObjectService {
	
	public FileObject saveFile(String fileName, Byte[] fileBytes);
	
	public Byte[] loadFile(String fileName);

}
