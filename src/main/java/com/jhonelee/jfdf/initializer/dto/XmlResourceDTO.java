package com.jhonelee.jfdf.initializer.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "resource")
@XmlAccessorType(value = XmlAccessType.NONE)
@XmlType(name = "resource")
public class XmlResourceDTO {

	@XmlAttribute(name = "type")
	private ResourceType resourceType;
	
	@XmlAttribute(name = "name")
	private String resourceName;
	
	@XmlAttribute(name = "code")
	private String resourceCode;

	@XmlAttribute(name = "url")
    private String url;
	
	@XmlAttribute(name = "httpMethod")
	private String httpMethod;
	
	@XmlAttribute(name = "iconType")
	private ResourceIconType resourceIconType;
	
	@XmlAttribute(name = "iconPath")
	private String iconPath;

	@XmlElement(name = "resource")
	private List<XmlResourceDTO> children = new ArrayList<XmlResourceDTO>();

	@XmlEnum
	public static enum ResourceType {
		SYSTEM,
		MENU,
		RESOURCE,
		BUTTON;
	}
	
	@XmlEnum
	public static enum ResourceIconType {
		ICON,
		IMG;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public ResourceIconType getResourceIconType() {
		return resourceIconType;
	}

	public void setResourceIconType(ResourceIconType resourceIconType) {
		this.resourceIconType = resourceIconType;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public List<XmlResourceDTO> getChildren() {
		return children;
	}

	public void setChildren(List<XmlResourceDTO> children) {
		this.children = children;
	}

}
