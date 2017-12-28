package com.jhonelee.jfdf.questionnaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonelee.jfdf.questionnaire.dto.QuestionnaireDTO;
import com.jhonelee.jfdf.questionnaire.entity.Questionnaire;
import com.jhonelee.jfdf.questionnaire.repository.QuestionnaireRepository;
import com.jhonelee.jfdf.questionnaire.service.QuestionnaireService;
import com.jhonelee.jfdf.web.convert.ConvertUtils;

/**
 * Created by User on 2017/12/21.
 */
@Controller
public class QuestionnaireController {

	@Autowired
	private QuestionnaireService surveyService;

	@Autowired
	private QuestionnaireRepository surveyRepository;

	@RequestMapping(value = "/questionnaire/page", method = RequestMethod.GET)
	public String questionnaire(Model model) {
		// return "questionnaire/questionnaire";
		// return "questionnaire/questionnaireAdd";
		model.addAttribute("city", this.surveyService.find(2L));
		return "questionnaire/questionnaire";
	}

	@RequestMapping(value = "/questionnaire", method = RequestMethod.POST)
	@ResponseBody
	public QuestionnaireDTO create(@RequestBody Questionnaire questionnaire) {
		// 创建问卷
		this.surveyService.saveOrUpdate(questionnaire);

		// if (questionnaire.getTid() == null || "".equals(questionnaire.getTid())){
		// this.surveyService.saveOrUpdate(questionnaire);
		// }else {
		// this.surveyRepository.delete(questionnaire.getTid());
		// questionnaire.setTid(null);
		// this.surveyService.saveOrUpdate(questionnaire);
		//
		//
		// }

		return ConvertUtils.convert(questionnaire, input -> {
			QuestionnaireDTO questionnaireDTO = new QuestionnaireDTO();
			questionnaireDTO.setTitle(input.getTitle());
			questionnaireDTO.setRemark(input.getRemark());
			questionnaireDTO.setState(input.getState());
			// questionnaireDTO.setProblemInfoList(input.getProblemInfoList().addAll());
			questionnaireDTO.setStarttime(input.getStarttime());
			return questionnaireDTO;
		});
	}

	@RequestMapping(value = "/questionnaires", method = RequestMethod.GET)
	@ResponseBody
	public Page<QuestionnaireDTO> find(@RequestParam(name = "title", required = false) String title, Pageable pageable) {
		// 首页显示问卷
		Page<Questionnaire> result = this.surveyService.find(title, pageable);

		return result.map(input -> {
			QuestionnaireDTO sto = new QuestionnaireDTO();
			sto.setTid(input.getTid());
			sto.setTitle(input.getTitle());
			sto.setRemark(input.getRemark());
			sto.setStarttime(input.getStarttime());
			sto.setState(input.getState());
			return sto;
		});
	}

	@RequestMapping(value = "/questionnaire/{tid}", method = RequestMethod.GET)
	@ResponseBody
	public Questionnaire read(@PathVariable("tid") Long tid) {
		// 根据tid查询问卷信息
		Questionnaire result = this.surveyService.find(tid);

		return result;
	}

	@RequestMapping(value = "/questionnaire/{tid}", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@PathVariable("tid") Long tid, @RequestBody Questionnaire questionnaire) {
		// 修改更新操作：1.先根据tid删除原有问卷，2.在新建一条数据（但问卷发布后不在具有编辑功能）
		this.surveyService.delete(tid);
		this.surveyService.saveOrUpdate(questionnaire);

		return "questionnaire/questionnaire";
	}

	@RequestMapping(value = "/questionnaire/{tid}/{state}", method = RequestMethod.GET)
	@ResponseBody
	public void delete(@PathVariable("tid") Long tid, @PathVariable("state") String state) {
		this.surveyService.updateStateBytid(tid, state);
	}

	// @RequestMapping(value = "/resource/{id}", method = RequestMethod.PUT)
	// @ResponseBody
	// public QuestionnaireDTO update(@PathVariable("id") Long id, @RequestBody
	// Questionnaire target) {
	// Questionnaire questionnaire = this.surveyRepository.findOne(id);
	//// resource.setResourceName(target.getResourceName());
	//// resource.setResourceCode(target.getResourceCode());
	//// resource.setHttpMethod(target.getHttpMethod());
	//// resource.setUrl(target.getUrl());
	//// resource.setDescription(target.getDescription());
	// this.surveyService.saveOrUpdate(target);
	//
	// return ConvertUtils.convert(resource, input -> {
	// ResourceDTO dto = new ResourceDTO();
	// dto.setId(input.getId());
	// dto.setResourceName(input.getResourceName());
	// dto.setResourceCode(input.getResourceCode());
	// dto.setUrl(input.getUrl());
	// dto.setHttpMethod(input.getHttpMethod());
	// dto.setDescription(input.getDescription());
	// return dto;
	// });
	// }
}
