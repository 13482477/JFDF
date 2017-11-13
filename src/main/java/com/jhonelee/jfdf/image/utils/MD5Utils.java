package com.jhonelee.jfdf.image.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5Utils {

	public static String getMd5FromBytes(byte[] input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input);
			
			byte[] digest = md.digest();
			return  new String(Hex.encodeHex(digest));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Md5 encode faild!", e);
		}
	}

//	public static String getMd5FromInputStream(InputStream is, MessageDigest md, int byteArraySize) {
//
//		md.reset();
//		byte[] bytes = new byte[byteArraySize];
//		int numBytes;
//		while ((numBytes = is.read(bytes)) != -1) {
//			md.update(bytes, 0, numBytes);
//		}
//		byte[] digest = md.digest();
//		String result = new String(Hex.encodeHex(digest));
//		return result;
//	}

}
