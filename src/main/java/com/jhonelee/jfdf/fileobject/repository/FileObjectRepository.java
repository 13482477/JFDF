package com.jhonelee.jfdf.fileobject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhonelee.jfdf.fileobject.entity.FileObject;

@Repository
public interface FileObjectRepository extends JpaRepository<FileObject, Long> {

}
