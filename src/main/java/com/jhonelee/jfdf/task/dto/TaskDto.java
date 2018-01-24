package com.jhonelee.jfdf.task.dto;

import com.jhonelee.jfdf.task.entity.Task;
import com.jhonelee.jfdf.task.entity.TaskStatus;

public class TaskDto {
	
	private Long id;

	private String cron;

	private TaskStatus taskStatus;

	private String taskName;

	private String taskGroup;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(String taskGroup) {
		this.taskGroup = taskGroup;
	}
	
	public Task createEntity() {
		Task task = new Task();
		task.setId(this.id);
		task.setCron(this.getCron());
		task.setTaskStatus(this.getTaskStatus());
		task.setTaskName(this.getTaskName());
		task.setTaskGroup(this.getTaskGroup());
		return task;
	}
	

}
