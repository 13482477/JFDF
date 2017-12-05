package com.jhonelee.jfdf.resource.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.repository.ResourceRepository;

@Component
public class ResourceValidator implements Validator {

	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return Resource.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Resource resource = (Resource) target;

		Resource original = resource.getId() == null ? null : this.resourceRepository.findOne(resource.getId());

		if (StringUtils.isBlank(resource.getResourceName())) {
			errors.rejectValue("resourceName", null, null, "请输入资源名称");
		}

		if (StringUtils.isBlank(resource.getResourceCode())) {
			errors.rejectValue("resourceCode", null, null, "请输入资源代码");
		}

		if (StringUtils.isBlank(resource.getHttpMethod())) {
			errors.rejectValue("httpMethod", null, null, "请输入请求方法");
		}

		if (this.isContinued(original, resource, "url") && StringUtils.isBlank(resource.getUrl())) {
			errors.rejectValue("url", null, null, "请输入url");
		}

		if (this.isContinued(original, resource, "resourceCode") && StringUtils.isNotBlank(resource.getResourceCode()) && this.resourceRepository.countByResourceCode(resource.getResourceCode()) > 0) {
			errors.rejectValue("resourceCode", null, null, "资源代码已存在");
		}
	}

	private boolean isContinued(Resource original, Resource target, String fieldName) {
		if (original != null && "resourceCode".equals(fieldName)) {
			return !StringUtils.equals(original.getResourceCode(), target.getResourceCode());
		} else if (original != null && "url".equals(fieldName)) {
			return !StringUtils.equals(original.getUrl(), target.getUrl());
		} else {
			return true;
		}
	}

}
