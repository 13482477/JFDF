package com.jhonelee.jfdf.questionnaire.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by WangCan on 2017/12/21.
 * 问卷表
 */
@Entity
@Table(name = "sys_questionnaire")
public class Questionnaire implements Serializable{
    private static final long serialVersionUID = -4546630039085469834L;
    //问卷表	id	title（问卷名称）	 remark（问卷说明）	starttime（问卷新建时间）
    // 	endtime （问卷终止时间）	state（状态（待发布0，发布1，暂停2，停止3））

    /**
     * 问卷id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
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

    @OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name="tid")
    private List<ProblemInfo> problemInfoList;

//    /**
//     * 问卷发给某个职位级别
//     */
//    @OneToMany(cascade={CascadeType.ALL})
//    @JoinColumn(name="leave")
//    private List<ProblemInfo> problemInfoList;



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

    public List<ProblemInfo> getProblemInfoList() {
        return problemInfoList;
    }

    public void setProblemInfoList(List<ProblemInfo> problemInfoList) {
        this.problemInfoList = problemInfoList;
    }
}
