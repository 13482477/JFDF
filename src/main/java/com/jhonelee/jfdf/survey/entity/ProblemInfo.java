package com.jhonelee.jfdf.survey.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by WangCan on 2017/12/21.
 * 问卷题目表
 */
@Entity
@Table(name = "sys_problemInfo")
public class ProblemInfo implements Serializable{
    private static final long serialVersionUID = -4546630039085469834L;
//    问题表	id	qid（问题id必须不一样（tid+题序号））	qtitle （问题标题）	tid(问卷id)		type(选项类型：单选，多选，问答)
//    optionname问题选项

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    /**
     * 问题id
     */
    @Id
    private String qid;

    /**
     * 问题标题
     */
    private String qtitle;

    /**
     * 问卷id
     */
    @Column(name="tid")
    private Long tid;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="qid")
    private List<OptionInfo> optionInfoList;

    /**
     * 选项类型：单选，多选，问答
     */
    private String type;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public List<OptionInfo> getOptionInfoList() {
        return optionInfoList;
    }

    public void setOptionInfoList(List<OptionInfo> optionInfoList) {
        this.optionInfoList = optionInfoList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
