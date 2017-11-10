package com.jhonelee.jfdf.fileobject.service;

import com.jhonelee.jfdf.fileobject.entity.FileObject;

public interface FileObjectService {
	
	public FileObject saveFile(String fileName, byte[] byteArray);
	
	public FileObject loadFile(String fileName);

}
