package com.jhonelee.jfdf.valitation.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;

@Component
public class ValidatorUtils {

	private static EntityManager entityManager;

	public static void validateEmpty(Errors errors, Object target, String... fields) {
		Assert.notNull(target, "Target object can not be null");

		BeanWrapper beanWrapper = new BeanWrapperImpl(target);

		for (String field : fields) {
			Object value = beanWrapper.getPropertyValue(field);
			if (value instanceof String && StringUtils.isBlank((CharSequence) value)) {
				errors.rejectValue(field, null, null, "请输入必填项");
			} else if (value == null) {
				errors.rejectValue(field, null, null, "请输入必填项");
			}
		}
	}

	public static void validateUnique(Errors errors, Object target, String[] fields, Class<?> targetClazz, String primaryKey) {
		Assert.notNull(fields, "The array of fields is not be null!");

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery(targetClazz);
		Root<?> root = criteriaQuery.from(targetClazz);

		List<Predicate> predicates = new ArrayList<Predicate>();

		BeanWrapper beanWrapper = new BeanWrapperImpl(target);

		for (int i = 0; i < fields.length; i++) {
			String field = fields[i];
			Object value = beanWrapper.getPropertyValue(field);

			Predicate predicate = StringUtils.equals(field, primaryKey) ? criteriaBuilder.notEqual(root.get(field), value) : criteriaBuilder.equal(root.get(field), value);
			predicates.add(predicate);
		}

		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		List<?> resultList = entityManager.createQuery(criteriaQuery).getResultList();

		if (CollectionUtils.isNotEmpty(resultList)) {

			List<String> fieldsList = Arrays.asList(fields);

			fieldsList.stream().filter(input -> input.equals(primaryKey));

			errors.reject(null, StringUtils.join(fieldsList, ",") + " 数据已存在");
		}
	}

	public static void validateStartWith(Errors errors, Object target, String field, String startValue) {
		Assert.notNull(target, "Target object must be required!");
		Assert.notNull(field, "Filed must be required!");
		Assert.notNull(startValue, "StartValue must be required");

		BeanWrapper beanWrapper = new BeanWrapperImpl(target);

		Object fieldValue = beanWrapper.getPropertyValue(field);

		Assert.isTrue(fieldValue instanceof String, "Value type mest be java.lang.String");

		if (StringUtils.startsWith((CharSequence) fieldValue, startValue)) {
			errors.rejectValue(field, null, null, "必须以" + startValue + "开头");
		}
	}
	
	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		ValidatorUtils.entityManager = entityManager;
	}

}
