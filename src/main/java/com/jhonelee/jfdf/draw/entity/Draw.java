package com.jhonelee.jfdf.draw.entity;

import com.jhonelee.jfdf.employee.entity.Employee;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "draw")
public class Draw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 抽奖时间
     */
    private Date date;

    /**
     * 奖项
     */
    private Integer award;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getAward() {
        return award;
    }

    public void setAward(Integer award) {
        this.award = award;
    }
}
