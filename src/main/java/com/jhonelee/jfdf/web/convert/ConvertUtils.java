package com.jhonelee.jfdf.web.convert;

import org.springframework.core.convert.converter.Converter;

public class ConvertUtils {
	
	public static <S, T> T convert(S source, Converter<S, T> converter) {
		return converter.convert(source);
	}

}
