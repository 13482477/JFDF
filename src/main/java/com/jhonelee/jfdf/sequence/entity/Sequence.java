package com.jhonelee.jfdf.sequence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "sys_sequence", uniqueConstraints = @UniqueConstraint(columnNames = "sequenceName"))
public class Sequence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String sequenceName;
	
	private Integer currentValue = 0;
	
	private Integer step = 1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public Integer getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

}
