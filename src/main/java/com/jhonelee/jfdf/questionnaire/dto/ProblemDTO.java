package com.jhonelee.jfdf.questionnaire.dto;

import java.util.List;

/**
 * Created by User on 2017/12/22.
 */
public class ProblemDTO {
    /**
     * 问题id
     */
    private String qid;

    /**
     * 问题标题
     */
    private String qtitle;


    /**
     * 选项类型：单选，多选，问答
     */
    private String type;

    private List<OptionDTO> optionInfoList;

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQtitle() {
        return qtitle;
    }

    public void setQtitle(String qtitle) {
        this.qtitle = qtitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OptionDTO> getOptionInfoList() {
        return optionInfoList;
    }

    public void setOptionInfoList(List<OptionDTO> optionInfoList) {
        this.optionInfoList = optionInfoList;
    }
}
