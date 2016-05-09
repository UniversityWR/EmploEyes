package com.br.emploeyes.bean;

import com.br.emploeyes.model.Company;
import com.br.emploeyes.model.Role;
import com.br.emploeyes.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean; 
import javax.faces.context.FacesContext;
 
@ManagedBean
@ApplicationScoped
public class CompanyListBean {
    
    private List<Company> companyList = new ArrayList<>(); 
    
    public List<Company> getCompanyList() {
        return companyList;
    } 
    public List<Company> getCompanyListByRole(Role role) {
        
        List<Company> newCompanyList = new ArrayList<>();
        newCompanyList.clear();
        for(Company company : companyList){
            if(company.getId() == role.getCompanyId()){
                newCompanyList.add(company);
            }
        } 
        return newCompanyList;
    }  
    
    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    } 
         
    public String saveCompany(Company company, Role role){  
        role.setCompanyId(company.getId());
        companyList.add(company);
        return "role_form?faces-redirect=true";
    }   
    public String openCompanyForm(Role role){   
        return "company_form?faces-redirect=true";
    }
}
