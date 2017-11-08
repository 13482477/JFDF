package com.jhonelee.jfdf.sequence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhonelee.jfdf.sequence.entity.Sequence;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, Long> {
	
	public Sequence getBySequenceName(String sequenceName);
	

}
