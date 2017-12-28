package com.jhonelee.jfdf.survey.service;

import com.jhonelee.jfdf.resource.entity.Resource;
import com.jhonelee.jfdf.resource.repository.ResourceRepository;
import com.jhonelee.jfdf.survey.entity.Questionnaire;
import com.jhonelee.jfdf.survey.repository.SurveyRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017/12/21.
 */
@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Transactional
    public void saveOrUpdate(Questionnaire questionnaire) { this.surveyRepository.save(questionnaire);
    }

    public Page<Questionnaire> find(String title, Pageable pageable) {
        return this.surveyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (StringUtils.isNotBlank(title)) {
                predicates.add(criteriaBuilder.like(root.get("title"), '%'+title+'%'));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
        }, pageable);
    }

    public Questionnaire find(Long tid) {
        return this.surveyRepository.findOne(tid);
    }

    public Page<Questionnaire> findbyTitle(String title,Pageable pageable) {
        return this.surveyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (StringUtils.isNotBlank(title)) {
                predicates.add(criteriaBuilder.equal(root.get("title"), title));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
        }, pageable);
    }

    @Transactional
    public void updateStateBytid(Long tid,String state) {
        this.surveyRepository.updateStateBytid(tid,state);
    }


    @Transactional
    public void delete(Long tid) {
        this.surveyRepository.delete(tid);
    }
}
