package com.br.emploeyes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String city;
    private String province;
    private String country;
    private String branch;
    private String description;
    @OneToMany
    private final List<Function> functionList = new ArrayList<>();

    public Company() {
    } 
    
    public void addRole(Function role) {
        functionList.add(role);
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
        return province;
    }

    public void setState(String state) {
        this.province = state;
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

    public List<Function> getRoleList() {
        return functionList;
    }

}
