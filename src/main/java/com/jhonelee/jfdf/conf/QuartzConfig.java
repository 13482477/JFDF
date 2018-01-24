package com.jhonelee.jfdf.conf;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.Scheduler;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.jhonelee.jfdf.initializer.QuartzScriptInitializer;

@Configuration
public class QuartzConfig {

	@Autowired
	private DataSource dataSource;

	public static class AutowireSpringBeanJobFactory extends SpringBeanJobFactory {

		@Autowired
		private AutowireCapableBeanFactory capableBeanFactory;

		@Override
		protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
			Object jobInstance = super.createJobInstance(bundle);
			this.capableBeanFactory.autowireBean(jobInstance);
			return jobInstance;
		}

	}

	@Bean
	public AutowireSpringBeanJobFactory jobFactory() {
		AutowireSpringBeanJobFactory jobFactory = new AutowireSpringBeanJobFactory();
		return jobFactory;
	}

	@Bean
	public Properties quartzProperties() {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		try {
			propertiesFactoryBean.afterPropertiesSet();
			return propertiesFactoryBean.getObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setDataSource(this.dataSource);
		schedulerFactoryBean.setQuartzProperties(quartzProperties());
		schedulerFactoryBean.setJobFactory(this.jobFactory());
		return schedulerFactoryBean;
	}

	@Bean
	public Scheduler scheduler() {
		return schedulerFactoryBean().getScheduler();
	}
	
	@Bean
	public QuartzScriptInitializer quartzScriptInitializer() {
		QuartzScriptInitializer quartzScriptInitializer = new QuartzScriptInitializer();
		return quartzScriptInitializer;
	}
	

}
