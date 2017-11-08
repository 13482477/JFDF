package com.jhonelee.jfdf.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {
	
	@Test
	public void regexTest() {
		Pattern pattern = Pattern.compile("abc(.*?)abc");
		Matcher matcher = pattern.matcher("abc3443abcfgjhgabcgfjabc");
		if(matcher.find()) {
			String str = matcher.group(1);
			System.out.println(str);
		}
	}
	
	@Test
	public void regex2Test() {
//		Pattern pattern = Pattern.compile("[[\\u4e00-\\u9fa5]{11,}[0-9]{1,}]{12,}");
		Pattern pattern = Pattern.compile("(?=([\\u4e00-\\u9fa5]|[0-9]){12,})");
		Matcher matcher = pattern.matcher("啊啊啊啊啊啊啊啊2啊啊啊啊");
		if(matcher.find()) {
			String str = matcher.group(0);
			System.out.println(str);
		}
	}

}
