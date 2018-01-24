package com.jhonelee.jfdf.initializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.jdbc.DataSourceInitializedEvent;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.config.SortedResourcesFactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configurable
public class QuartzScriptInitializer implements ApplicationListener<DataSourceInitializedEvent> {

	private static Logger LOGGER = LoggerFactory.getLogger(QuartzScriptInitializer.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private DataSourceProperties dataSourceProperties;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void init() {
		if (this.dataSource == null) {
			LOGGER.debug("No DataSource found so not initializing");
			return;
		}

		if (this.isInitilized()) {
			LOGGER.debug("The DatabaseTable of quartz requirement is initilized.");
			return;
		}
		
		this.runSchemaScript();
		LOGGER.info("Successful create quartz database table.");
	}

	private boolean isInitilized() {
		List<Map<String, Object>> result = this.jdbcTemplate.queryForList("SHOW TABLES LIKE 'QRTZ_JOB_DETAILS'");
		return result.size() != 0;
	}

	private void runSchemaScript() {
		List<Resource> scripts = getScripts("spring.datasource.schema", this.dataSourceProperties.getSchema(), "schema");
		this.runScripts(scripts);
	}

	private List<Resource> getScripts(String propertyName, List<String> resources, String fallback) {
		if (resources != null) {
			return getResources(propertyName, resources, true);
		}
		String platform = this.dataSourceProperties.getPlatform();
		List<String> fallbackResources = new ArrayList<String>();
		fallbackResources.add("classpath*:" + fallback + "-" + platform + ".sql");
		fallbackResources.add("classpath*:" + fallback + ".sql");
		return getResources(propertyName, fallbackResources, false);
	}

	private List<Resource> getResources(String propertyName, List<String> locations, boolean validate) {
		List<Resource> resources = new ArrayList<Resource>();
		for (String location : locations) {
			for (Resource resource : doGetResources(location)) {
				if (resource.exists()) {
					resources.add(resource);
				} else if (validate) {
					throw new ResourceNotFoundException(propertyName, resource);
				}
			}
		}
		return resources;
	}

	private Resource[] doGetResources(String location) {
		try {
			SortedResourcesFactoryBean factory = new SortedResourcesFactoryBean(this.applicationContext, Collections.singletonList(location));
			factory.afterPropertiesSet();
			return factory.getObject();
		} catch (Exception ex) {
			throw new IllegalStateException("Unable to load resources from " + location, ex);
		}
	}
	
	private void runScripts(List<Resource> resources) {
		if (resources.isEmpty()) {
			return;
		}
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(this.dataSourceProperties.isContinueOnError());
		populator.setSeparator(this.dataSourceProperties.getSeparator());
		if (this.dataSourceProperties.getSqlScriptEncoding() != null) {
			populator.setSqlScriptEncoding(this.dataSourceProperties.getSqlScriptEncoding().name());
		}
		for (Resource resource : resources) {
			populator.addScript(resource);
		}
		DatabasePopulatorUtils.execute(populator, this.dataSource);
	}

	@Override
	public void onApplicationEvent(DataSourceInitializedEvent event) {
		
	}

}
