package com.jhonelee.jfdf.systemsituation.service;

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
	
	public SystemSituation getSystemSituation() {
		List<SystemSituation> systemSituations = this.systemSituationRepository.findOrderByIdDesc();
		return CollectionUtils.isNotEmpty(systemSituations) ? systemSituations.get(0) : null;
	}

}
