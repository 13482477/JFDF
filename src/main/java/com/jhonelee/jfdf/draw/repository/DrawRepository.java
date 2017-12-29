package com.jhonelee.jfdf.draw.repository;

import com.jhonelee.jfdf.draw.entity.Draw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DrawRepository extends JpaRepository<Draw, Long>, JpaSpecificationExecutor<Draw> {
}
