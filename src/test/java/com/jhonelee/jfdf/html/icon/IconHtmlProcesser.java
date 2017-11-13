package com.jhonelee.jfdf.html.icon;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class IconHtmlProcesser {
	
	@Test
	public void extractIconValue() {
		
		File file = new File(this.getClass().getResource("").getPath() + "/Icon.html");
		SAXReader saxReader = new SAXReader();
		
		try {
			Document document = saxReader.read(file);
			String xmlPath = "//div[@class='col-md-3 col-sm-4']";
			
			@SuppressWarnings("unchecked")
			List<Element> elements = document.selectNodes(xmlPath);
			
			for (Element element : elements) {
				System.out.println(element.getTextTrim());
			}
			
			String xmlPath2 = "//div[@id='glyphicons']/ul/li//span[@class='glyphicon-class']";
			
			@SuppressWarnings("unchecked")
			List<Element> element2 = document.selectNodes(xmlPath2);
			
			for (Element element : element2) {
				System.out.println(element.getTextTrim());
			}
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

}
