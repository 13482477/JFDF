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
public class SystemInitializer implements CommandLineRunner{

	@Autowired
	private SystemSituationService systmSituationService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DatabaseMetadataSource securityMetadataSource;
	
	@Autowired
	private MenuService menuService;

	@Override
	public void run(String... args) throws Exception {
		if (!this.systmSituationService.isSystemInitialized()) {
			this.resourceService.initResource();
			this.authorityService.initAuthority();
			this.roleService.initRole();
			this.userService.initAdminUser();
			this.systmSituationService.initSystemSituatiopn();
		}
		this.securityMetadataSource.refreshRequestMap();
		this.menuService.refreshSystemMenu();
	}
	
}
