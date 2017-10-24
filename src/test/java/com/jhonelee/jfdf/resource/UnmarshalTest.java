package com.jhonelee.jfdf.resource;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;

import com.jhonelee.jfdf.resource.entity.Resource;

public class UnmarshalTest {
	
	@Test
	public void unmarshalResourceTest() {
		try {
			
			InputStream inputStream = UnmarshalTest.class.getClassLoader().getResourceAsStream("resource.xml");
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Resource.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			Resource resource = (Resource) unmarshaller.unmarshal(inputStream);
			
			Assert.assertNotNull(resource);
			Assert.assertNotEquals(resource.getChildren().size(), 2);
			Assert.assertNotEquals(resource.getChildren().get(1).getChildren().size(), 4);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
