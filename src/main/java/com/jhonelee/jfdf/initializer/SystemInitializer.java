package com.jhonelee.jfdf.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jhonelee.jfdf.initializer.service.SystemInitializeService;
import com.jhonelee.jfdf.menu.service.MenuService;
import com.jhonelee.jfdf.requestmapping.service.RequestMappingService;
import com.jhonelee.jfdf.security.metadatasource.DatabaseMetadataSource;

@Component
@Order(1)
public class SystemInitializer implements CommandLineRunner {
	
	private @Autowired SystemInitializeService systemInitializeService;
	
	private @Autowired DatabaseMetadataSource securityMetadataSource;

	private @Autowired MenuService menuService;
	
	private @Autowired RequestMappingService requestMappingService;
	
	@Override
	public void run(String... args) throws Exception {
		this.initializeSystemData();
		this.initializeAccessPermission();
		this.initializeSystemMenu();
		this.requestMappingService.refreshCachedData();
	}

	private void initializeSystemMenu() {
		this.menuService.refreshNavigationMenu();
	}

	private void initializeAccessPermission() {
		this.securityMetadataSource.refreshRequestMap();
	}

	private void initializeSystemData() {
		if (!this.systemInitializeService.isSystemInitialized()) {
			this.systemInitializeService.initializingSystemSituatiopn();
			this.systemInitializeService.initMenuAndResource();
			this.systemInitializeService.initAuthority();
			this.systemInitializeService.initRole();
			this.systemInitializeService.initAdminUser();
			this.systemInitializeService.initializedSystemSituatiopn();
		}
	}
	
}
