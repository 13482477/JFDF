package com.jhonelee.jfdf.menu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.authority.entity.Authority;
import com.jhonelee.jfdf.menu.dto.NavigationMenuDto;
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
	public void saveAndUpdate(Menu menu) {
		this.menuRepository.save(menu);
	}

	@Transactional
	public void refreshNavigationMenu() {
		Menu rootMenu = this.menuRepository.findByParent(null);
		if (rootMenu != null) {
			NavigationMenuDto menuDto = this.createNavigationMenuDto(rootMenu);
			this.setNavigationMenuDtoReference(menuDto, rootMenu);
			putMenuIntoServletContext(menuDto);
		}

	}

	private void setNavigationMenuDtoReference(NavigationMenuDto menuDto, Menu menu) {
		for (Menu menu1 : menu.getChildren()) {
			NavigationMenuDto menuDto1 = this.createNavigationMenuDto(menu1);
			menuDto1.setParent(menuDto);
			menuDto.getChildren().add(menuDto1);
			this.setNavigationMenuDtoReference(menuDto1, menu1);
		}
	}

	private NavigationMenuDto createNavigationMenuDto(Menu menu) {
		NavigationMenuDto menuDto = new NavigationMenuDto();
		menuDto.setId(menu.getId());
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

	private void putMenuIntoServletContext(NavigationMenuDto menuDto) {
		if (this.servletContext != null) {
			servletContext.setAttribute(MENU_ATTRIBUTE_NAME, menuDto);
		}
	}

	public List<Menu> loadByParentId(Long id) {
		return this.menuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			return id == null ? criteriaBuilder.isNull(root.get("parent")) : criteriaBuilder.equal(root.get("parent").get("id"), id);
		}, new Sort(Sort.Direction.ASC, "id"));
	}

}
