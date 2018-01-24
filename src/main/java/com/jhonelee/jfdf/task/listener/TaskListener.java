package com.jhonelee.jfdf.task.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

@Component
public class TaskListener implements JobListener {

	@Override
	public String getName() {
		return "globalTaskListener";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		
	}

}
