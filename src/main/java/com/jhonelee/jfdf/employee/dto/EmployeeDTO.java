package com.jhonelee.jfdf.employee.dto;


public class EmployeeDTO {

    private Long id;

    private String name;

    private String staffId;

    private Boolean investor;

    private Integer division;

    private Boolean signed;

    private Boolean winning;

    private Integer award;

    private Boolean regular;

    private Boolean gift;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

    public EmployeeDTO() {
    }

    public EmployeeDTO(Boolean investor, Integer division, Boolean signed, Boolean winning, Integer award, Boolean regular, Boolean gift) {
        this.investor = investor;
        this.division = division;
        this.signed = signed;
        this.winning = winning;
        this.award = award;
        this.regular = regular;
        this.gift = gift;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }

    public Boolean getWinning() {
        return winning;
    }

    public void setWinning(Boolean winning) {
        this.winning = winning;
    }

    public Integer getAward() {
        return award;
    }

    public void setAward(Integer award) {
        this.award = award;
    }

    public Boolean getRegular() {
        return regular;
    }

    public void setRegular(Boolean regular) {
        this.regular = regular;
    }

    public Boolean getGift() {
        return gift;
    }

    public void setGift(Boolean gift) {
        this.gift = gift;
    }
}
