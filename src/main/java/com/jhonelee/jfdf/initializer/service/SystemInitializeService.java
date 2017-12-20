package com.jhonelee.jfdf.initializer.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.jhonelee.jfdf.authority.entity.Authority;
import com.jhonelee.jfdf.authority.repository.AuthorityRepository;
import com.jhonelee.jfdf.initializer.dto.XmlResourceDTO;
import com.jhonelee.jfdf.initializer.dto.XmlResourceDTO.ResourceType;
import com.jhonelee.jfdf.menu.entity.IconType;
import com.jhonelee.jfdf.menu.entity.Menu;
import com.jhonelee.jfdf.menu.repository.MenuRepository;
import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.repository.ResourceRepository;
import com.jhonelee.jfdf.role.entity.Role;
import com.jhonelee.jfdf.role.repository.RoleRepository;
import com.jhonelee.jfdf.systemsituation.entity.SystemSituation;
import com.jhonelee.jfdf.systemsituation.repository.SystemSituationRepository;
import com.jhonelee.jfdf.user.entity.User;
import com.jhonelee.jfdf.user.repository.UserRepository;

@Service
public class SystemInitializeService {
	
	private String filePath = "resource.xml";

	@Autowired
	private ResourceRepository resourceRepository;
	
	@Autowired
	private SystemSituationRepository systemSituationRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Transactional
	public void initMenuAndResource() {

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(XmlResourceDTO.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			XmlResourceDTO xmlResourceDTO = (XmlResourceDTO) unmarshaller.unmarshal(inputStream);

			Menu menu = this.transformToNavigationTree(xmlResourceDTO, null);
			
			this.menuRepository.save(menu);
			
		} catch (JAXBException e) {
			throw new RuntimeException("Resource init exception!", e);
		}
	}
	
	private Menu transformToNavigationTree(XmlResourceDTO resourceDTO, Menu parent) {
		Menu menu = this.createMenu(resourceDTO, parent);
		
		for (XmlResourceDTO resourceDto1 : resourceDTO.getChildren()) {
			if (ResourceType.MENU.equals(resourceDto1.getResourceType())) {
				menu.getChildren().add(this.transformToNavigationTree(resourceDto1, menu));
			}
			else if (ResourceType.RESOURCE.equals(resourceDto1.getResourceType())) {
				menu.getResources().add(this.createResource(resourceDto1, menu));
			}
			else {
				return null;
			}
		}
		
		return menu;
	}
	
	private Menu createMenu(XmlResourceDTO resourceDTO, Menu parent) {
		Menu menu = new Menu();
		menu.setName(resourceDTO.getResourceName());
		menu.setMenuCode(resourceDTO.getResourceCode());
		menu.setUrl(resourceDTO.getUrl());
		menu.setIconType(IconType.valueOf(resourceDTO.getResourceIconType().name()));
		menu.setIconPath(resourceDTO.getIconPath());
		menu.setParent(parent);
		return menu;
	}
	
	private Resource createResource(XmlResourceDTO resourceDTO, Menu parent) {
		Resource resource = new Resource();
		resource.setResourceName(resourceDTO.getResourceName());
		resource.setResourceCode(resourceDTO.getResourceCode());
		resource.setUrl(resourceDTO.getUrl());
		resource.setHttpMethod(resourceDTO.getHttpMethod());
		return resource;
	}
	
//	private void setResourceReference(Menu resource) {
//		for (Resource resource1 : resource.getChildren()) {
//			resource1.setParent(resource);
//			this.setResourceReference(resource1);
//		}
//	}
	
	public boolean isSystemInitialized() {
		SystemSituation systemSituation = this.systemSituationRepository.getByTagString(SystemSituation.TAG_STRING);
		return systemSituation == null || SystemSituation.InitializationStatus.UNINITIALIZED.equals(systemSituation.getInitializationStatus()) ? false : true;
	}
	
	@Transactional
	public void initializingSystemSituatiopn() {
		SystemSituation entity = new SystemSituation();
		entity.setTagString(SystemSituation.TAG_STRING);
		entity.setInitializationDate(new Date());
		entity.setInitializationStatus(SystemSituation.InitializationStatus.INITIALIZING);
		this.systemSituationRepository.save(entity);
	}
	
	@Transactional
	public void initAuthority() {
		List<Resource> allResource = this.resourceRepository.findAll();
		allResource.forEach(resource -> {
			Authority authority = new Authority();
			authority.setAuthorityCode("role_" + resource.getResourceCode());
			authority.setAuthorityName(resource.getResourceName());
			authority.setDescription(resource.getDescription());
			authority.getResources().add(resource);
			this.authorityRepository.save(authority);
		});
	}
	
	@Transactional
	public void initRole() {
		List<Authority> authorities = this.authorityRepository.findAll();
		
		Role role = new Role();
		role.setRoleCode("administrator");
		role.setRoleName("系统管理员");
		role.setDescription("系统管理员");
		role.getAuthorities().addAll(authorities);
		this.roleRepository.save(role);
	}
	
	@Transactional
	public void initAdminUser() {
		List<Role> roles = this.roleRepository.findAll();

		User user = new User();
		user.setUsername("admin");
		user.setPassword("password");
		user.setActive(true);
		user.setEmail("13482477@qq.com");
		user.setMobile("13482432953");
		user.setNickname("李志强");
		user.getRoles().addAll(roles);
		
		//密码加密
		user.setPassword(this.encodePasswordFromUser(user));
		this.userRepository.save(user);
	}
	
	@SuppressWarnings("deprecation")
	private String encodePasswordFromUser(User user) {
		final PasswordEncoder passwordEncoderDelegate = this.passwordEncoder;

		org.springframework.security.authentication.encoding.PasswordEncoder passwordEncoder2 = new org.springframework.security.authentication.encoding.PasswordEncoder() {
			public String encodePassword(String rawPass, Object salt) {
				checkSalt(salt);
				return passwordEncoderDelegate.encode(rawPass);
			}

			public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
				checkSalt(salt);
				return passwordEncoderDelegate.matches(rawPass, encPass);
			}

			private void checkSalt(Object salt) {
				Assert.isNull(salt, "Salt value must be null when used with crypto module PasswordEncoder");
			}
		};

		return passwordEncoder2.encodePassword(user.getPassword(), null);
	}
	
	@Transactional
	public void initializedSystemSituatiopn() {
		SystemSituation entity = this.systemSituationRepository.getByTagString(SystemSituation.TAG_STRING);
		entity.setInitializationStatus(SystemSituation.InitializationStatus.INITIALIZED);
		this.systemSituationRepository.save(entity);
	}

}
