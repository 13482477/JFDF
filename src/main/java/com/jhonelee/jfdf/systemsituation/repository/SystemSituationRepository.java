package com.jhonelee.jfdf.systemsituation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jhonelee.jfdf.systemsituation.entity.SystemSituation;

@Repository
public interface SystemSituationRepository extends JpaRepository<SystemSituation, Long>, JpaSpecificationExecutor<SystemSituation> {
	

}
