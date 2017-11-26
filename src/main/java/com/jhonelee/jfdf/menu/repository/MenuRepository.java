package com.jhonelee.jfdf.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhonelee.jfdf.menu.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	public Menu findByParent(Menu parent);

}
