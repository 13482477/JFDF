package com.jhonelee.jfdf.task.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 定时任务
 */
@Entity
@Table(name = "sys_task")
public class Task implements Serializable {

	private static final long serialVersionUID = 4770323568753176029L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 时间表达式
	 */
	private String cron;

	/**
	 * 使用状态
	 */
	@Column(name = "task_status", length = 10)
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;

	/**
	 * 任务名称
	 */
	private String taskName;

	/**
	 * 任务组
	 */
	private String taskGroup;
	
	/**
	 * 任务参数
	 */
	@OneToMany(mappedBy = "task")
	private List<TaskAttribute> taskAttributes = new ArrayList<TaskAttribute>();

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

	public List<TaskAttribute> getTaskAttributes() {
		return taskAttributes;
	}

	public void setTaskAttributes(List<TaskAttribute> taskAttributes) {
		this.taskAttributes = taskAttributes;
	}
	
}
