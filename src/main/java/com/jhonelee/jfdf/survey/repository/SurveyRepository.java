package com.jhonelee.jfdf.survey.repository;

import com.jhonelee.jfdf.survey.entity.AnswerInfo;
import com.jhonelee.jfdf.survey.entity.Questionnaire;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Questionnaire, Long>, JpaSpecificationExecutor<Questionnaire> {

	public Long countByTid(String tid);

	/**
	 * title
	 * @param title
	 * @return
	 */
	@Query("select a from Questionnaire a where a.title like '%:title%'")
	List<Questionnaire> findByUserName(@Param("title") String title);
	public Long countByTitle(String title);

//	public Long countByUrl(String url);
@Modifying
@Query("update Questionnaire sc set sc.state = ?2 where sc.tid = ?1")
	public int updateStateBytid(Long tid,String state);

}
