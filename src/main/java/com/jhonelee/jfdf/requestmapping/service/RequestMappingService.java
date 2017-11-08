package com.jhonelee.jfdf.requestmapping.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

	private TreeMap<String, List<RequestMapping>> requestMappingsTree = new TreeMap<String, List<RequestMapping>>();
	
	private String[] execludeUrls = {"/error", "/swagger"};

	public List<RequestMapping> findRequestMappings(String searchParams) {
		List<RequestMapping> requestMappings = new ArrayList<RequestMapping>();
		SortedMap<String, List<RequestMapping>> subMap = requestMappingsTree.tailMap(searchParams == null ? "" : searchParams);
		for (Entry<String, List<RequestMapping>> entry : subMap.entrySet()) {
			requestMappings.addAll(entry.getValue());
		}
		return requestMappings;
	}

	public void refreshCachedData() {
		List<RequestMapping> requestMappings = this.extractRequestMappingsFromWebApplicationContext();
		this.groupRequestMappingByUrl(requestMappings);
	}

	private void groupRequestMappingByUrl(List<RequestMapping> requestMappings) {
		for (RequestMapping requestMapping : requestMappings) {
			String lowerCaseUrl = StringUtils.lowerCase(requestMapping.getUrl());
			if (requestMappingsTree.get(lowerCaseUrl) == null) {
				requestMappingsTree.put(lowerCaseUrl, new ArrayList<RequestMapping>());
			}
			requestMappingsTree.get(lowerCaseUrl).add(requestMapping);
		}
	}

	private List<RequestMapping> extractRequestMappingsFromWebApplicationContext() {
		List<RequestMapping> requestMappings = new ArrayList<RequestMapping>();

		RequestMappingHandlerMapping bean = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();

		for (Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
			Set<String> urls = entry.getKey().getPatternsCondition().getPatterns();
			
			if (this.execludeMatch(urls)) {
				continue;
			}
			
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
			} else {
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
	
	private boolean execludeMatch(Set<String> urls) {
		for (String url : urls) {
			for (String execludeUrl : this.execludeUrls) {
				if (StringUtils.startsWith(url, execludeUrl)) {
					return true;
				}
			}
		}
		return false;
	}

}
