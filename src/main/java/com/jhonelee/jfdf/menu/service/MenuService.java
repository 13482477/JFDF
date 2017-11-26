package com.jhonelee.jfdf.menu.service;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.authority.entity.Authority;
import com.jhonelee.jfdf.menu.dto.MenuDto;
import com.jhonelee.jfdf.menu.entity.Menu;
import com.jhonelee.jfdf.menu.repository.MenuRepository;
import com.jhonelee.jfdf.resource.entity.Resource;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private ServletContext servletContext;

	private static String MENU_ATTRIBUTE_NAME = "__SYSTEM_MENU";

	@Transactional
	private void saveAndUpdate(Menu menu) {
		this.menuRepository.save(menu);
	}

	@Transactional
	public void refreshSystemMenu() {
		Menu rootMenu = this.menuRepository.findByParent(null);
		if (rootMenu != null) {
			MenuDto menuDto = this.createMenuDto(rootMenu);
			this.setMenuDtoReference(menuDto, rootMenu);
			putMenuIntoServletContext(menuDto);
		}

	}

	private void setMenuDtoReference(MenuDto menuDto, Menu menu) {
		for (Menu menu1 : menu.getChildren()) {
			MenuDto menuDto1 = this.createMenuDto(menu1);
			menuDto1.setParent(menuDto);
			menuDto.getChildren().add(menuDto1);
			this.setMenuDtoReference(menuDto1, menu1);
		}
	}

	private MenuDto createMenuDto(Menu menu) {
		MenuDto menuDto = new MenuDto();
		menuDto.setMenuName(menu.getName());
		menuDto.setIconType(menu.getIconType());
		menuDto.setIconPath(menu.getIconPath());
		menuDto.setUrl(menu.getUrl());
		menuDto.getAuthorities().addAll(this.extractAuthorityMarkFromResource(menu));
		return menuDto;
	}

	private Set<String> extractAuthorityMarkFromResource(Menu menu) {
		Set<String> result = new HashSet<String>();
		for (Resource resource : menu.getResource()) {
			for (Authority authority : resource.getAuthorities()) {
				result.add(authority.getAuthorityCode());
			}
		}
		return result;
	}

	private void putMenuIntoServletContext(MenuDto menuDto) {
		if (this.servletContext != null) {
			servletContext.setAttribute(MENU_ATTRIBUTE_NAME, menuDto);
		}
	}

}
