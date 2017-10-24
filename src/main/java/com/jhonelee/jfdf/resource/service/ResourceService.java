package com.jhonelee.jfdf.resource.service;

import java.io.InputStream;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.repository.ResourceRepository;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepository resourceRepository;
	
	private String filePath = "resource.xml";

	@Transactional
	public void saveOrUpdate(Resource resource) {
		this.resourceRepository.save(resource);
	}

	public List<Resource> findAll() {
		return this.resourceRepository.findAll();
	}
	

	@Transactional
	public void initResource() {

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Resource.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			Resource resource = (Resource) unmarshaller.unmarshal(inputStream);

			this.setResourceReference(resource);

			this.resourceRepository.save(resource);
		} catch (JAXBException e) {
			throw new RuntimeException("Resource init exception!", e);
		}
	}
	
	private void setResourceReference(Resource resource) {
		for (Resource resource1 : resource.getChildren()) {
			resource1.setParent(resource);
			this.setResourceReference(resource1);
		}
	}

}
