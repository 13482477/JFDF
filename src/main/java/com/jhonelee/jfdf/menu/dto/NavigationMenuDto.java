package com.jhonelee.jfdf.menu.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jhonelee.jfdf.menu.entity.IconType;
import com.jhonelee.jfdf.resource.entity.Resource;

public class NavigationMenuDto {
	
	private String menuName;
	
	private IconType iconType;
	
	private String iconPath;
	
	private String url;
	
	private Set<String> authorities = new HashSet<String>();
	
	private NavigationMenuDto parent;
	
	private List<NavigationMenuDto> children = new ArrayList<NavigationMenuDto>();
	
	private Set<Resource> resources = new HashSet<Resource>();

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public IconType getIconType() {
		return iconType;
	}

	public void setIconType(IconType iconType) {
		this.iconType = iconType;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	public NavigationMenuDto getParent() {
		return parent;
	}

	public void setParent(NavigationMenuDto parent) {
		this.parent = parent;
	}

	public List<NavigationMenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<NavigationMenuDto> children) {
		this.children = children;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

}
