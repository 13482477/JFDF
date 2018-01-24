package com.jhonelee.jfdf.taskinstance.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jhonelee.jfdf.task.entity.Task;

/**
 * 任务实例
 */
@Entity
@Table(name = "sys_task_instance")
public class TaskInstance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 任务名称
	 */
	private String taskName;
	
	/**
	 * 任务分组
	 */
	private String taskGroup;
	
	/**
	 * 任务实例状态
	 */
	@Column(name = "task_instance_status", length = 10)
	@Enumerated(EnumType.STRING)
	private TaskInstanceStatus taskInstanceStatus;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	/**
	 * 执行时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date executeDate;
	
	/**
	 * 完成时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishedDate;
	
	/**
	 * 任务
	 */
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TaskInstanceStatus getTaskInstanceStatus() {
		return taskInstanceStatus;
	}

	public void setTaskInstanceStatus(TaskInstanceStatus taskInstanceStatus) {
		this.taskInstanceStatus = taskInstanceStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}

	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
