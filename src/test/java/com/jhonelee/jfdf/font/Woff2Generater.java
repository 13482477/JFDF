package com.jhonelee.jfdf.font;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Woff2Generater {

	@Test
	public void wgetWoff2FromFontCss() {
		String content = readerString();
		
		List<String> urls = this.parseUrl(content);
		
		


	}

	private String readerString() {
		StringBuffer stringBuffer = new StringBuffer();

		File file = new File(this.getClass().getResource("").getPath() + "/font.css");

		FileInputStream fileInputStream = null;
		BufferedReader bufferReader = null;
		InputStreamReader inputStreamReader = null;
		try {
			fileInputStream = new FileInputStream(file);

			inputStreamReader = new InputStreamReader(fileInputStream);
			bufferReader = new BufferedReader(inputStreamReader);

			String line;
			while ((line = bufferReader.readLine()) != null) {
				stringBuffer.append(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				bufferReader.close();
				inputStreamReader.close();
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return stringBuffer.toString();
	}
	
	private List<String> parseUrl(String content) {
		List<String> result = new ArrayList<String>();
		
		String patternStr = "(https://\\S*.woff2)";

		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(content);

		while(matcher.find()) {
			int count = matcher.groupCount();
			for (int i = 0; i < count; i ++) {
				result.add(matcher.group(i));
			}
		}
		return result;
	}
	
}
