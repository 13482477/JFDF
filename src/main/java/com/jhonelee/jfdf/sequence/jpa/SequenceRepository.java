package com.jhonelee.jfdf.sequence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jhonelee.jfdf.sequence.entity.Sequence;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, Long> {
	
	public Sequence getBySequenceName(String sequenceName);
	
	@Modifying(clearAutomatically = true)
	@Query("update "
			+ "Sequence s "
			+ "set "
			+ "s.currentValue = s.currentValue + s.step "
			+ "where "
			+ "s.sequenceName = :sequenceName")
	public void updateCurrentValue(@Param("sequenceName")String sequenceName);
	
	@Query("select "
			+ "s.currentValue "
			+ "from Sequence s "
			+ "where "
			+ "s.sequenceName = :sequenceName")
	public Long getCurrentValue(@Param("sequenceName")String sequenceName);

}
