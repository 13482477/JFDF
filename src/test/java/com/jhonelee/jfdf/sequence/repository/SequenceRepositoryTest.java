package com.jhonelee.jfdf.sequence.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhonelee.jfdf.Application;
import com.jhonelee.jfdf.sequence.jpa.SequenceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SequenceRepositoryTest {
	
	@Autowired
	private SequenceRepository sequenceRepository;
	
	@Test
	public void getCurrentValueTest() {
		Long current = this.sequenceRepository.getCurrentValue("GLOBAL");
		Assert.assertTrue(current != null);
	}

}
