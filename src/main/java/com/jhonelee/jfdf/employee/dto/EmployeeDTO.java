package com.jhonelee.jfdf.employee.dto;


public class EmployeeDTO {

    private Long id;

    private String name;

    private String staffId;

    private Boolean awarded;

    private Boolean medalist;

    private Boolean signed;

    private Boolean winning;

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

    public Boolean getAwarded() {
        return awarded;
    }

    public void setAwarded(Boolean awarded) {
        this.awarded = awarded;
    }

    public Boolean getMedalist() {
        return medalist;
    }

    public void setMedalist(Boolean medalist) {
        this.medalist = medalist;
    }

    public EmployeeDTO() {
    }

    public EmployeeDTO(Boolean awarded, Boolean medalist, Boolean signed, Boolean winning) {
        this.awarded = awarded;
        this.medalist = medalist;
        this.signed = signed;
        this.winning = winning;
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
}
