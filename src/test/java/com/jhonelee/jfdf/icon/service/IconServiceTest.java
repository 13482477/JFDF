package com.jhonelee.jfdf.icon.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhonelee.jfdf.Application;
import com.jhonelee.jfdf.icon.entity.Icon;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class IconServiceTest {
	
	@Autowired
	private IconService iconService;
	
	@Test
	public void getFontAwesomeGroupBySubGroupTest() {
		
		LinkedHashMap<String, List<Icon>> data = this.iconService.findFontAwesomeGroupBySubGroup();
		
		for (Entry<String, List<Icon>> entry : data.entrySet()) {
			for (Icon icon : entry.getValue()) {
				System.out.println(entry.getKey() + "	" + icon.getValue());
			}
		}
		
	}

}
