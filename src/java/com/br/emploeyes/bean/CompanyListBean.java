package com.br.emploeyes.bean;

import com.br.emploeyes.model.Company;
import com.br.emploeyes.model.Function;
import com.br.emploeyes.model.Employee;
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
    public List<Company> getCompanyListByRole(Function role) {
        
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
         
    public String saveCompany(Company company, Function role){  
        role.setCompanyId(company.getId());
        companyList.add(company);
        return "role_form?faces-redirect=true";
    }   
    public String openCompanyForm(Function role){   
        return "company_form?faces-redirect=true";
    }
}
