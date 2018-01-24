package com.jhonelee.jfdf.task.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.task.dto.TaskDto;
import com.jhonelee.jfdf.task.entity.Task;
import com.jhonelee.jfdf.task.entity.TaskStatus;
import com.jhonelee.jfdf.task.repository.TaskRepository;
import com.jhonelee.jfdf.task.service.TaskService;
import com.jhonelee.jfdf.task.validator.TaskDtoValidator;
import com.jhonelee.jfdf.user.dto.UserDto;
import com.jhonelee.jfdf.web.convert.ConvertUtils;
import com.jhonelee.jfdf.web.error.ErrorResult;
import com.jhonelee.jfdf.web.validator.field.FieldValidationResult;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskDtoValidator taskDtoValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.taskDtoValidator);
	}

	@RequestMapping(value = "/task/page", method = RequestMethod.GET)
	public String renderPage() {
		return "task/task";
	}

	@RequestMapping(value = "/task/validation", method = RequestMethod.GET)
	@ResponseBody
	public FieldValidationResult validateField(TaskDto taskDto) {
		FieldValidationResult result = new FieldValidationResult();
		// result.setValid(ValidatorUtils.evaluateUnique(taskDto, Task.class, "id",
		// "username"));
		return result;
	}

	@RequestMapping(value = "/task", method = RequestMethod.POST)
	@ResponseBody
	public TaskDto create(@RequestBody @Validated TaskDto taskDto) {
		Task task = taskDto.createEntity();

		this.taskService.save(task);
		return ConvertUtils.convert(task, input -> {
			TaskDto result = new TaskDto();
			BeanUtils.copyProperties(input, result);
			return result;
		});
	}

	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
	@ResponseBody
	public TaskDto read(@PathVariable Long id) {
		Task task = this.taskRepository.findOne(id);
		return ConvertUtils.convert(task, input -> {
			TaskDto result = new TaskDto();
			BeanUtils.copyProperties(input, result);
			return result;
		});
	}

	@RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public TaskDto update(@PathVariable("id") Long id, @RequestBody @Validated UserDto target) {
		Task task = this.taskRepository.findOne(id);
		BeanUtils.copyProperties(target, task);

		this.taskService.update(task);
		return ConvertUtils.convert(task, input -> {
			TaskDto result = new TaskDto();
			BeanUtils.copyProperties(input, result);
			return result;
		});
	}

	@RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		this.taskService.delete(id);
	}

	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	@ResponseBody
	public Page<TaskDto> find(@RequestParam(required = false) String cron, @RequestParam(required = false) TaskStatus taskStatus, @RequestParam(required = false) String taskName,
			@RequestParam(required = false) String taskGroup, Pageable pageable) {
		Page<Task> result = this.taskService.find(cron, taskStatus, taskName, taskGroup, pageable);
		return result.map(input -> {
			TaskDto taskDto = new TaskDto();
			BeanUtils.copyProperties(input, taskDto);
			return taskDto;
		});
	}
	
	@RequestMapping(value = "/task/{id}/start", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> start(@PathVariable Long id) {
		Task task = this.taskRepository.findOne(id);
		
		if (task == null) {
			ErrorResult errorResult = new ErrorResult();
			errorResult.setStatus(HttpStatus.BAD_REQUEST.value());
			errorResult.setMessage("该任务不存在");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
		}
		
		this.taskService.start(id);
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(value = "/task/{id}/pause", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> pause(@PathVariable Long id) {
		Task task = this.taskRepository.findOne(id);
		
		if (task == null) {
			ErrorResult errorResult = new ErrorResult();
			errorResult.setStatus(HttpStatus.BAD_REQUEST.value());
			errorResult.setMessage("该任务不存在");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
		}
		
		this.taskService.pause(id);
		return ResponseEntity.ok(null);
	}
}
