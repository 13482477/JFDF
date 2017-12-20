package com.jhonelee.jfdf.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jhonelee.jfdf.menu.entity.Menu;
import com.jhonelee.jfdf.resource.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long>, JpaSpecificationExecutor<Resource> {
	
	public Long countByResourceCode(String resourceCode);
	
	public Long countByUrl(String url);
	
	public List<Resource> findByMenus(List<Menu> menus);
	
}
