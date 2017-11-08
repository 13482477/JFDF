package com.jhonelee.jfdf.sequence.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhonelee.jfdf.Application;
import com.jhonelee.jfdf.sequence.entity.Sequence;
import com.jhonelee.jfdf.sequence.jpa.SequenceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SequenceServiceTest {

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private SequenceRepository sequenceRepository;

	@Test
	public void createSequenceTest() {
		String sequenceName = "GLOBAL";
		
		Sequence existSequence = this.sequenceRepository.getBySequenceName(sequenceName);

		if (existSequence == null) {
			Sequence sequence = this.sequenceService.createSequence(sequenceName, 1, new Long(100000));
			Assert.assertNotNull(sequence.getId());
		}
	}
	
	@Test
	public void getNextValueTest() {
		String sequenceName = "GLOBAL";
		
		Long value = this.sequenceService.getNextValue(sequenceName);
		
		Assert.assertNotNull(value);
	}
	
	

}
