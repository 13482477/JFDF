package com.jhonelee.jfdf.employee.entity;

import com.jhonelee.jfdf.draw.entity.Draw;
import com.jhonelee.jfdf.sign.entity.Sign;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 员工号
     */
    private String staffId;

    /**
     * 员工姓名
     */
    private String name;


    /**
     * 是否投资人
     */
    private Boolean investor;

    /**
     * 师部
     */
    private Integer division;


    /**
     * 签到记录
     */
    @OneToMany(mappedBy = "employee")
    private List<Sign> signList = new ArrayList<>();

    /**
     * 抽奖记录
     */
    @OneToMany(mappedBy = "employee")
    private List<Draw> drawList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getInvestor() {
        return investor;
    }

    public void setInvestor(Boolean investor) {
        this.investor = investor;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public List<Sign> getSignList() {
        return signList;
    }

    public void setSignList(List<Sign> signList) {
        this.signList = signList;
    }

    public List<Draw> getDrawList() {
        return drawList;
    }

    public void setDrawList(List<Draw> drawList) {
        this.drawList = drawList;
    }
}
