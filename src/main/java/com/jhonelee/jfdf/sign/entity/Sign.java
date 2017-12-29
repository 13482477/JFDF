package com.jhonelee.jfdf.sign.entity;

import com.jhonelee.jfdf.employee.entity.Employee;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sign")
public class Sign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 签到时间
     */
    private Date date;

    /**
     * 签到位置
     */
    private String position;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
