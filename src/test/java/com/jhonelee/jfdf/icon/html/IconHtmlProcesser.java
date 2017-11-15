package com.jhonelee.jfdf.icon.html;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhonelee.jfdf.Application;
import com.jhonelee.jfdf.icon.entity.Icon;
import com.jhonelee.jfdf.icon.entity.IconType;
import com.jhonelee.jfdf.icon.service.IconService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class IconHtmlProcesser {
	
	@Autowired
	private IconService iconService;
	
	@Test
	public void extractIconValue() {
		
		File file = new File(this.getClass().getResource("").getPath() + "/Icon.html");
		SAXReader saxReader = new SAXReader();
		
		try {
			Document document = saxReader.read(file);
			
			@SuppressWarnings("unchecked")
			List<Element> sectionElements = document.selectNodes("//section");
			
			for (Element sectionElement : sectionElements) {
				@SuppressWarnings("unchecked")
				String subGroupName = ((List<Element>)sectionElement.elements("h4")).get(0).getTextTrim();
				
				@SuppressWarnings("unchecked")
				List<Element> iconDivs = ((List<Element>)sectionElement.elements("div")).get(0).elements("div");
				for (Element element : iconDivs) {
					Icon icon = this.iconService.save(IconType.FONT_AWESOME, subGroupName, element.getTextTrim(), element.getTextTrim());
					System.out.println(icon.getSubGroup() + "	" + icon.getValue());
				}
			}
			
			
			
			String xmlPath2 = "//div[@id='glyphicons']/ul/li//span[@class='glyphicon-class']";
			
			@SuppressWarnings("unchecked")
			List<Element> element2 = document.selectNodes(xmlPath2);
			
			for (Element element : element2) {
				Icon icon = this.iconService.save(IconType.GLYPHICON, null, element.getTextTrim(), StringUtils.split(element.getTextTrim())[1]);
				System.out.println(icon.getValue());
			}
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
