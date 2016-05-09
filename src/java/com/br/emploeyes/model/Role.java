package com.br.emploeyes.model;

import java.util.UUID;

public class Role {

    private long id;
    private long userId;
    private long companyId;
    private int startYear;
    private int endYear;
    private double salary;
    private String name;
    private String feedback;

    public Role() {
        this.id = UUID.randomUUID().getMostSignificantBits();
    }
 
    public Role(String name, long userId, int startYear, int endYear, double salary, String feedback) {
        this();
        this.name = name;
        this.userId = userId;
        this.startYear = startYear;
        this.endYear = endYear;
        this.salary = salary;
        this.feedback = feedback;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
}
