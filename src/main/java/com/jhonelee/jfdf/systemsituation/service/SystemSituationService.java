package com.jhonelee.jfdf.systemsituation.service;

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
	

}
