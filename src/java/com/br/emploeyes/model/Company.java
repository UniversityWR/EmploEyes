package com.br.emploeyes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Company {

    private long id;
    private String name;
    private String city;
    private String state;
    private String country;
    private String branch;
    private String description;
    private final List<Role> roleList;

    public Company() {
        this.roleList = new ArrayList<>();
        this.id = UUID.randomUUID().getMostSignificantBits();
    }
    
    public Company(String name, String branch, String description) {
        this();
        this.name = name;
        this.branch = branch;
        this.description = description;
    }

    public void addRole(Role role) {
        roleList.add(role);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

}
