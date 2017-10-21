package com.jhonelee.jfdf.initializer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jhonelee.jfdf.authority.entity.Authority;
import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.entity.Resource.ResourceType;
import com.jhonelee.jfdf.resource.service.ResourceService;
import com.jhonelee.jfdf.systemsituation.entity.SystemSituation;
import com.jhonelee.jfdf.systemsituation.service.SystemSituationService;

@Component
@Order(1)
public class SystemInitializer implements CommandLineRunner{

	@Autowired
	private SystemSituationService systmSituationService;
	
	@Autowired
	private ResourceService resourceService;

	@Override
	public void run(String... args) throws Exception {
		SystemSituation systemSituation = this.systmSituationService.getSystemSituation();

		if (!this.isSystemInitialized(systemSituation)) {
			SystemSituation entity = new SystemSituation();
			entity.setInitializationDate(new Date());
			entity.setInitializationStatus(SystemSituation.InitializationStatus.INITIALIZING);
			this.systmSituationService.saveOrUpdate(entity);
		}
		
		
	}
	
	private boolean isSystemInitialized(SystemSituation systemSituation) {
		return systemSituation == null || SystemSituation.InitializationStatus.UNINITIALIZED.equals(systemSituation.getInitializationStatus()) ? false : true;
	}
	
	private void initResource() {
		Resource jfdf = new Resource();
		jfdf.setResourceName("JFDF");
		jfdf.setResourceCode("JFDF");
		jfdf.setResourceType(ResourceType.SYSTEM);
		jfdf.setDescription("JFDF");
		
		Authority authority = new Authority();
		
		
	}
	
}
