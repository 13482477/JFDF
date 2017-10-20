package com.jhonelee.security.authority.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jhonelee.security.authority.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>, JpaSpecificationExecutor<Authority> {

}
