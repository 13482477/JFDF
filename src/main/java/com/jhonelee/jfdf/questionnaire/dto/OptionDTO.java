package com.jhonelee.jfdf.questionnaire.dto;

/**
 * Created by User on 2017/12/22.
 */
public class OptionDTO {
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

    /**
     * 是否允许为空
     */
    private String ornull;

    /**
     * 是否必填
     */
    private String orwrite;

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
