package com.jhonelee.jfdf.systemsituation.service;

import java.util.Date;

import javax.transaction.Transactional;

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
		SystemSituation systemSituation = this.systemSituationRepository.getByTagString(SystemSituation.TAG_STRING);
		return systemSituation == null || SystemSituation.InitializationStatus.UNINITIALIZED.equals(systemSituation.getInitializationStatus()) ? false : true;
	}
	
	@Transactional
	public void initializingSystemSituatiopn() {
		SystemSituation entity = new SystemSituation();
		entity.setTagString(SystemSituation.TAG_STRING);
		entity.setInitializationDate(new Date());
		entity.setInitializationStatus(SystemSituation.InitializationStatus.INITIALIZING);
		this.systemSituationRepository.save(entity);
	}
	@Transactional
	public void initializedSystemSituatiopn() {
		SystemSituation entity = this.systemSituationRepository.getByTagString(SystemSituation.TAG_STRING);
		entity.setInitializationStatus(SystemSituation.InitializationStatus.INITIALIZED);
		this.systemSituationRepository.save(entity);
	}

}
