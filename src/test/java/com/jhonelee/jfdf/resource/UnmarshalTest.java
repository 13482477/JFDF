package com.jhonelee.jfdf.resource;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;

import com.jhonelee.jfdf.initializer.dto.XmlResourceDTO;

public class UnmarshalTest {
	
	@Test
	public void unmarshalResourceTest() {
		try {
			
			InputStream inputStream = UnmarshalTest.class.getClassLoader().getResourceAsStream("resource.xml");
			
			JAXBContext jaxbContext = JAXBContext.newInstance(XmlResourceDTO.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();	
			
			XmlResourceDTO resource = (XmlResourceDTO) unmarshaller.unmarshal(inputStream);
			
			Assert.assertNotNull(resource);
			Assert.assertNotEquals(resource.getChildren().size(), 2);
			Assert.assertNotEquals(resource.getChildren().get(1).getChildren().size(), 4);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
