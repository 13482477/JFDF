package com.jhonelee.jfdf.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jhonelee.jfdf.authority.service.AuthorityService;
import com.jhonelee.jfdf.menu.service.MenuService;
import com.jhonelee.jfdf.resource.service.ResourceService;
import com.jhonelee.jfdf.role.service.RoleService;
import com.jhonelee.jfdf.security.metadatasource.DatabaseMetadataSource;
import com.jhonelee.jfdf.systemsituation.service.SystemSituationService;
import com.jhonelee.jfdf.user.service.UserService;

@Component
@Order(1)
public class SystemInitializer implements CommandLineRunner {

	private @Autowired SystemSituationService systmSituationService;

	private @Autowired AuthorityService authorityService;

	private @Autowired ResourceService resourceService;

	private @Autowired RoleService roleService;

	private @Autowired UserService userService;

	private @Autowired DatabaseMetadataSource securityMetadataSource;

	private @Autowired MenuService menuService;

	@Override
	public void run(String... args) throws Exception {
		this.initializeSystemData();
		this.initializeAccessPermission();
		this.initializeSystemMenu();
	}

	private void initializeSystemMenu() {
		this.menuService.refreshSystemMenu();
	}

	private void initializeAccessPermission() {
		this.securityMetadataSource.refreshRequestMap();
	}

	private void initializeSystemData() {
		if (!this.systmSituationService.isSystemInitialized()) {
			this.systmSituationService.initializingSystemSituatiopn();
			this.resourceService.initResource();
			this.authorityService.initAuthority();
			this.roleService.initRole();
			this.userService.initAdminUser();
			this.systmSituationService.initializedSystemSituatiopn();
		}
	}

}
