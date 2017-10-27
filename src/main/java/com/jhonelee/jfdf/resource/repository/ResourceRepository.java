package com.jhonelee.jfdf.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jhonelee.jfdf.resource.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long>, JpaSpecificationExecutor<Resource> {
	
	@Query("select r from Resource r where r.parent IS NULL and r.resourceType = 'SYSTEM'")
	public Resource getTopLevelResource();
	
	@Query("select r from Resource r where r.parent.id = :parentId")
	public List<Resource> loadResourcesByParentId(@Param("parentId")Long parentId);

}
