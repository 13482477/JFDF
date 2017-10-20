package com.jhonelee.security.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jhonelee.security.resource.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long>, JpaSpecificationExecutor<Resource> {

}
