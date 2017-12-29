package com.jhonelee.jfdf.sign.repository;

import com.jhonelee.jfdf.sign.entity.Sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SignRepository extends JpaRepository<Sign, Long>, JpaSpecificationExecutor<Sign> {
}
