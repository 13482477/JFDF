package com.jhonelee.jfdf.requestmapping.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.jhonelee.jfdf.requestmapping.dto.RequestMapping;

@Service
public class RequestMappingService {

	private final static Logger LOGGER = LoggerFactory.getLogger(RequestMappingService.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

//	@Autowired
//	private ResourceRepository resourceRepository;

	public List<RequestMapping> extractRequestMappingsFromWebApplicationContext() {
		List<RequestMapping> requestMappings = new ArrayList<RequestMapping>();
		
		RequestMappingHandlerMapping bean = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
		
		for (Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
			Set<String> urls = entry.getKey().getPatternsCondition().getPatterns();
			Set<RequestMethod> requestMethods = new HashSet<RequestMethod>();
			if (CollectionUtils.isEmpty(entry.getKey().getMethodsCondition().getMethods())) {
				requestMethods.add(RequestMethod.DELETE);
				requestMethods.add(RequestMethod.GET);
				requestMethods.add(RequestMethod.HEAD);
				requestMethods.add(RequestMethod.OPTIONS);
				requestMethods.add(RequestMethod.PATCH);
				requestMethods.add(RequestMethod.POST);
				requestMethods.add(RequestMethod.PUT);
				requestMethods.add(RequestMethod.TRACE);
			}
			else {
				requestMethods.addAll(entry.getKey().getMethodsCondition().getMethods());
			}
			
			for (String url : urls) {
				for (RequestMethod requestMethod : requestMethods) {
					RequestMapping requestMapping = new RequestMapping();
					requestMapping.setUrl(url);
					requestMapping.setHttpMethod(requestMethod.name());
					requestMapping.setClassName(entry.getValue().getBeanType().getName());
					requestMappings.add(requestMapping);
					LOGGER.debug("RequestInfo:url=[{}],httpMethod=[{}],className=[{}]", requestMapping.getUrl(), requestMapping.getHttpMethod(), requestMapping.getClassName());
				}
			}
		}
		

		return requestMappings;
	}

//	private Set<String> getDbReqeustMappingLabels() {
//		Set<String> result = new HashSet<String>();
//		
//		List<Resource> allResources = this.resourceRepository.findAll();
//		Map<String, Set<String>> groupedResource = this.convertListToMapGroupByUrl(allResources);
//		
//		for (Entry<String, Set<String>> entry : groupedResource.entrySet()) {
//			if (CollectionUtils.isNotEmpty(entry.getValue())) {
//				for (String requestMethod : entry.getValue()) {
//					result.add(entry.getKey() + requestMethod);
//				}
//			}
//			else {
//				result.add(entry.getKey() + RequestMethod.GET);
//				result.add(entry.getKey() + RequestMethod.POST);
//				result.add(entry.getKey() + RequestMethod.PUT);
//				result.add(entry.getKey() + RequestMethod.HEAD);
//				result.add(entry.getKey() + RequestMethod.OPTIONS);
//				result.add(entry.getKey() + RequestMethod.PATCH);
//				result.add(entry.getKey() + RequestMethod.TRACE);
//				result.add(entry.getKey() + RequestMethod.DELETE);
//			}
//			
//		}
//
//		return result;
//	}
//
//	private Map<String, Set<String>> convertListToMapGroupByUrl(List<Resource> resources) {
//		Map<String, Set<String>> result = new HashMap<String, Set<String>>();
//		for (Resource resource : resources) {
//			if (result.get(resource.getUrl()) == null) {
//				Set<String> requestMethods = new HashSet<String>();
//				result.put(resource.getUrl(), requestMethods);
//			}
//
//			if (StringUtils.isNotEmpty(resource.getHttpMethod())) {
//				result.get(resource.getUrl()).add(resource.getHttpMethod());
//			}
//		}
//
//		return result;
//	}

}
