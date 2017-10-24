package com.jhonelee.jfdf.systemsituation.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.systemsituation.entity.SystemSituation;
import com.jhonelee.jfdf.systemsituation.repository.SystemSituationRepository;

@Service
public class SystemSituationService {
	
	@Autowired
	private SystemSituationRepository systemSituationRepository;
	
	@Transactional
	public void saveOrUpdate(SystemSituation entity) {
		this.systemSituationRepository.saveAndFlush(entity);
	}
	
	public boolean isSystemInitialized() {
		List<SystemSituation> systemSituations = this.systemSituationRepository.findAll();
		
		SystemSituation systemSituation = CollectionUtils.isNotEmpty(systemSituations) ? systemSituations.get(systemSituations.size() - 1) : null;
		return systemSituation == null || SystemSituation.InitializationStatus.UNINITIALIZED.equals(systemSituation.getInitializationStatus()) ? false : true;
	}
	
	@Transactional
	public void initSystemSituatiopn() {
		SystemSituation entity = new SystemSituation();
		entity.setInitializationDate(new Date());
		entity.setInitializationStatus(SystemSituation.InitializationStatus.INITIALIZING);
		this.systemSituationRepository.save(entity);
	}

}
