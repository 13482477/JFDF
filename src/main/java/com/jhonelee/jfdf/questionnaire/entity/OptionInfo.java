package com.jhonelee.jfdf.questionnaire.entity;

import javax.persistence.*;

/**
 * Created by WangCan on 2017/12/21.
 * 选项题目表
 */
@Entity
@Table(name = "sys_optionInfo")
public class OptionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 选项id
     */
    private String oid;

    /**
     * 选项内容
     */
    private String option_name;

    /**
     * 问题id
     */
    private String qid;

//    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
//    @JoinColumn(name="qid")
//    private ProblemInfo problemInfo;

    /**
     * 是否允许为空
     */
    private String ornull;

    /**
     * 是否必填
     */
    private String orwrite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getOrnull() {
        return ornull;
    }

    public void setOrnull(String ornull) {
        this.ornull = ornull;
    }

    public String getOrwrite() {
        return orwrite;
    }

    public void setOrwrite(String orwrite) {
        this.orwrite = orwrite;
    }
}
