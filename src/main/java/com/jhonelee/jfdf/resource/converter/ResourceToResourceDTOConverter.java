package com.jhonelee.jfdf.resource.converter;

import org.springframework.core.convert.converter.Converter;

import com.jhonelee.jfdf.resource.dto.ResourceDTO;
import com.jhonelee.jfdf.resource.entity.Resource;

public class ResourceToResourceDTOConverter implements Converter<Resource, ResourceDTO> {

	@Override
	public ResourceDTO convert(Resource source) {
		return null;
	}

}
