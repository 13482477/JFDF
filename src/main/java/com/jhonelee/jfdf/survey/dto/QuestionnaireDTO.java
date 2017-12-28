package com.jhonelee.jfdf.survey.dto;

import java.util.List;

/**
 * Created by User on 2017/12/21.
 */
public class QuestionnaireDTO {

    private Long tid;

    /**
     * 问卷名称
     */
    private String title;

    /**
     * 问卷说明
     */
    private String remark;
    /**
     * 问卷新建时间
     */
    private String starttime;

    /**
     * 问卷终止时间
     */
    private String endtime;
    /**
     * 状态（发布，暂停，停止）
     */
    private String state;

    private List<ProblemDTO> problemInfoList;

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ProblemDTO> getProblemInfoList() {
        return problemInfoList;
    }

    public void setProblemInfoList(List<ProblemDTO> problemInfoList) {
        this.problemInfoList = problemInfoList;
    }
}
