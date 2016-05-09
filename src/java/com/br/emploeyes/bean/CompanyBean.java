package com.br.emploeyes.bean;

import com.br.emploeyes.model.Company;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
 
@ManagedBean
@RequestScoped
public class CompanyBean {
    
    private Company company = new Company();

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    } 
    
    public String saveCompany(Company company){
        return "role_form?faces-redirect=true";
    }
}
