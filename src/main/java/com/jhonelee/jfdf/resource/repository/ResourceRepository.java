package com.jhonelee.jfdf.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jhonelee.jfdf.resource.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long>, JpaSpecificationExecutor<Resource> {
	
	@Query("select r from Resource r where r.parent IS NULL and r.resourceType = 'SYSTEM'")
	public Resource getTopLevelResource();

}
