package com.jhonelee.jfdf.web.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

public class BindingResultUtil {

	public static String parseErrorMessage(BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			return null;
		}
		
		List<String> errorStrs = new ArrayList<String>();
		
		CollectionUtils.collect(bindingResult.getAllErrors(), input -> input.getDefaultMessage(), errorStrs);
			
		return StringUtils.join(errorStrs, ",");
	}

}
