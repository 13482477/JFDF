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

}
