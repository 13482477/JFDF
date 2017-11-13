package com.jhonelee.jfdf.image.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.jhonelee.jfdf.image.utils.MD5Utils;

public class MD5UtilsTest {
	
	@Test
	public void getMd5FromBytesTest() {
		File file = new File("F:\\Blizzard\\World of Warcraft\\Wow-64.exe");
		byte[] byteArray = this.getByteArrayFromFile(file);
		
		String fileMD5 = MD5Utils.getMd5FromBytes(byteArray);
		
		Assert.assertNotNull(fileMD5);
		System.out.print(fileMD5);
		
	}
	
	private byte[] getByteArrayFromFile(File file) {
		byte[] result = null;
		
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] temp = new byte[1024];
			int n;
			while((n = fileInputStream.read(temp)) != -1) {
				byteArrayOutputStream.write(temp, 0, n);
			}
			
			byteArrayOutputStream.close();
			fileInputStream.close();
			
			result = byteArrayOutputStream.toByteArray();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Can not find file!", e);
		} catch (IOException e) {
			throw new RuntimeException("Faild to transform file to byte array!", e);
		}
		
		return result;
	}
 
}
