package com.jhonelee.jfdf.conf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreeMarkerConfig {

	@Autowired
	protected freemarker.template.Configuration configuration;
	
	@PostConstruct  
    public void  setSharedVariable(){
		
		
	}

}
