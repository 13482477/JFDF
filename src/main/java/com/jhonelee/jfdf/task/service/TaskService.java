package com.jhonelee.jfdf.task.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jhonelee.jfdf.task.entity.Task;
import com.jhonelee.jfdf.task.entity.TaskStatus;
import com.jhonelee.jfdf.task.job.SimpleJob;
import com.jhonelee.jfdf.task.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private TaskRepository taskRepository;

	@Transactional
	public void save(Task task) {
		this.taskRepository.save(task);
	}

	@Transactional
	public void update(Task task) {
		this.taskRepository.saveAndFlush(task);
	}

	@Transactional
	public void delete(Long id) {
		this.taskRepository.delete(id);
	}

	public Page<Task> find(String cron, TaskStatus taskStatus, String taskName, String taskGroup, Pageable pageable) {
		return this.taskRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotBlank(cron)) {
				predicates.add(criteriaBuilder.equal(root.get("cron"), cron));
			}

			if (taskStatus != null) {
				predicates.add(criteriaBuilder.equal(root.get("taskStatus"), taskStatus));
			}

			if (StringUtils.isNotBlank(taskName)) {
				predicates.add(criteriaBuilder.equal(root.get("taskName"), taskName));
			}

			if (StringUtils.isNotBlank(taskGroup)) {
				predicates.add(criteriaBuilder.equal(root.get("taskGroup"), taskGroup));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
		}, pageable);
	}

	public JobDetail getJobDetailFromSchema(Long taskId) {
		Task task = this.taskRepository.findOne(taskId);

		try {
			return task == null ? null : this.scheduler.getJobDetail(JobKey.jobKey(task.getTaskName(), task.getTaskGroup()));
		} catch (SchedulerException e) {
			throw new RuntimeException("Can not found job detail with JobKey", e);
		}
	}

	public void start(Long taskId) {
		Task task = this.taskRepository.findOne(taskId);

		if (task == null) {
			throw new RuntimeException("The task is not exists!");
		}

		try {
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getTaskGroup()).withSchedule(CronScheduleBuilder.cronSchedule(task.getCron())).build();
			
			JobDataMap jobDataMap = new JobDataMap();

			JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity(task.getTaskName(), task.getTaskGroup()).storeDurably(Boolean.TRUE).requestRecovery(Boolean.TRUE).build();
			
			JobKey jobKey = JobKey.jobKey(task.getTaskName(), task.getTaskGroup());
			
			if (!scheduler.checkExists(jobKey)) {
				scheduler.scheduleJob(jobDetail, trigger);
			}
			else {
				scheduler.resumeJob(jobKey);
			}
			
		} catch (SchedulerException e) {
			throw new RuntimeException("Start job field", e);
		}
	}

	public void pause(Long taskId) {
		Task task = this.taskRepository.findOne(taskId);

		if (task == null) {
			throw new RuntimeException("The task is not exists!");
		}

		try {
			this.scheduler.pauseJob(JobKey.jobKey(task.getTaskName(), task.getTaskGroup()));
		} catch (SchedulerException e) {
			throw new RuntimeException("Stop job field", e);
		}
	}

	public void stop(Long taskId) {
		Task task = this.taskRepository.findOne(taskId);

		if (task == null) {
			throw new RuntimeException("The task is not exists!");
		}

		try {
			this.scheduler.deleteJob(JobKey.jobKey(task.getTaskName(), task.getTaskGroup()));
		} catch (SchedulerException e) {
			throw new RuntimeException("Stop job field", e);
		}
	}

	private void updateTaskInScheduler(Task task) {
		TriggerKey triggerKey = TriggerKey.triggerKey(task.getTaskName(), task.getTaskGroup());
		try {
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}

			JobDetail jobDetail = JobBuilder.newJob().build();
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCron());
			trigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getTaskGroup()).withSchedule(scheduleBuilder).build();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

	private void registTask(Task task) {
		TriggerKey triggerKey = TriggerKey.triggerKey(task.getTaskName(), task.getTaskGroup());
		try {
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

	public void refreshTrigger() {
		try {
			// 查询出数据库中所有的定时任务
			List<Task> taskList = this.taskRepository.findAll();
			if (taskList != null) {
				for (Task task : taskList) {
					TaskStatus taskStatus = task.getTaskStatus();
					TriggerKey triggerKey = TriggerKey.triggerKey(task.getTaskName(), task.getTaskGroup());
					CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

					if (null == trigger) {
						if (TaskStatus.DISABLE.equals(taskStatus)) {
							continue;
						}

						JobDetail jobDetail = null;
						// 创建JobDetail（数据库中job_name存的任务全路径，这里就可以动态的把任务注入到JobDetail中）
						jobDetail = JobBuilder.newJob().build();
						CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCron());
						trigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskName(), task.getTaskGroup()).withSchedule(scheduleBuilder).build();

						scheduler.scheduleJob(jobDetail, trigger);
					} else { // 说明查出来的这条任务，已经设置到quartz中了
						// Trigger已存在，先判断是否需要删除，如果不需要，再判定是否时间有变化
						if (TaskStatus.ENABLE.equals(taskStatus)) { // 如果是禁用，从quartz中删除这条任务
							JobKey jobKey = JobKey.jobKey(task.getTaskName(), task.getTaskGroup());
							scheduler.deleteJob(jobKey);
							continue;
						}
						String searchCron = task.getCron(); // 获取数据库的
						String currentCron = trigger.getCronExpression();
						if (!searchCron.equals(currentCron)) { // 说明该任务有变化，需要更新quartz中的对应的记录
							// 表达式调度构建器
							CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);

							// 按新的cronExpression表达式重新构建trigger
							trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

							// 按新的trigger重新设置job执行
							scheduler.rescheduleJob(triggerKey, trigger);
						}
					}
				}
			}
		} catch (Exception e) {
		}

	}

}
