package com.jhonelee.jfdf.fileobject.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhonelee.jfdf.Application;
import com.jhonelee.jfdf.fileobject.entity.FileObject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FileObjectServiceTest {

	@Autowired
	private FileObjectService fileObjectService;

	@Test
	public void saveFileTest() {
		String filePath = "E:\\照片\\IMG_20150923_222457.jpg";
		filePath = filePath.replaceAll("\\\\|/", "\\" + File.separator);

		File file = new File(filePath);
		String originalFileName = StringUtils.substringAfterLast(filePath, File.separator);
		byte[] byteArray = this.readFileToByteArray(file);
		FileObject fileObject = this.fileObjectService.saveFile(originalFileName, byteArray);
		Assert.assertNotNull(fileObject);
	}
	
	@Test
	public void loadFileTest() {
		FileObject fileObject = this.fileObjectService.loadFile("100003.jpg");
		
		File file = this.createFile("d:/" + fileObject.getFileName());
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(fileObject.getContent());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Assert.assertNotNull(fileObject);
		Assert.assertNotNull(fileObject.getContent());
		
	}
	
	private File createFile(String filePath) {
		File file = new File(filePath);
		
		if (file.exists()) {
			file.delete();
		}
		
		try {
			file.createNewFile(); 
			return file;
		} catch (IOException e) {
			throw new RuntimeException("Can not create file!");
		}
	}
	
	
	
	

	private byte[] readFileToByteArray(File file) {

		FileInputStream fileInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;

		try {
			fileInputStream = new FileInputStream(file);
			byteArrayOutputStream = new ByteArrayOutputStream();

			byte[] temp = new byte[1024];
			int len;
			while ((len = fileInputStream.read(temp)) != -1) {
				byteArrayOutputStream.write(temp, 0, len);
			}

			return byteArrayOutputStream.toByteArray();
		} catch (FileNotFoundException e) {
			new RuntimeException("Can not found file!", e);
		} catch (IOException e) {
			new RuntimeException("IO exception", e);
		} finally {
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
