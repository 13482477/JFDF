package com.jhonelee.jfdf.menu.service;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.jhonelee.jfdf.menu.dto.MenuDto;
import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.repository.ResourceRepository;

@Service
public class MenuService {
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	private static String MENU_ATTRIBUTE_NAME = "__SYSTEM_MENU";
	
	@Transactional
	public void refreshSystemMenu() {
		Resource topLevelResource = this.resourceRepository.getTopLevelResource();
		
		if (topLevelResource != null) {
			MenuDto menuDto = this.createMenuDto(topLevelResource);
			this.setMenuDtoReference(menuDto, topLevelResource);
			putMenuIntoServletContext(menuDto);
		}
		
	}
	
	private void setMenuDtoReference(MenuDto menuDto, Resource resource) {
		resource.getChildren().forEach(resource1 -> {
			if (Resource.ResourceType.RESOURCE.equals(resource1.getResourceType())) {
				return;
			}
			MenuDto menuDto1 = this.createMenuDto(resource1);
			menuDto1.setParent(menuDto);
			menuDto.getChildren().add(menuDto1);
			this.setMenuDtoReference(menuDto1, resource1);
		});
	}
	
	private MenuDto createMenuDto(Resource resource) {
		MenuDto menu = new MenuDto();
		menu.setMenuName(resource.getResourceName());
		menu.setIconType(resource.getResourceIconType());
		menu.setIconPath(resource.getIconPath());
		menu.setUrl(resource.getUrl());
		menu.getAuthorities().addAll(this.extractAuthorityMarkFromResource(resource));
		return menu;
	}
	
	private Set<String> extractAuthorityMarkFromResource(Resource resource) {
		Set<String> result = new HashSet<String>();
		resource.getAuthorities().forEach(authority -> {
			result.add(authority.getAuthorityCode());
		});
		return result;
	}
	
	private void putMenuIntoServletContext(MenuDto menuDto) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		if (webApplicationContext != null) {
			ServletContext servletContext = webApplicationContext.getServletContext();
			if (servletContext != null) {
				servletContext.setAttribute(MENU_ATTRIBUTE_NAME, menuDto);
			}
		}
	}
	

}
