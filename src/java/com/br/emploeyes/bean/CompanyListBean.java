package com.br.emploeyes.bean;

import com.br.emploeyes.dao.Dao;
import com.br.emploeyes.model.Company;
import com.br.emploeyes.model.Function;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CompanyListBean implements Serializable {

    private String searchName;
    private Long searchId;

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public List<Company> getCompanyList() {
        Dao<Company> dao = Dao.newInstance();
        return dao.getAll(Company.class);
    }
    
    public Company getCompanyById(Long id){
        Dao<Company> dao = Dao.newInstance();
        if(id == null){
            return null;
        }
        return dao.getById(Company.class,id);                
    }

    public List<Company> getCompanyListByName() { 
        if (getSearchName() != null && !getSearchName().equals("")) {
            List<Company> newCompanyList = new ArrayList<>();
            newCompanyList.clear();
            for (Company company : getCompanyList()) {
                if (company.getName().toLowerCase().startsWith(getSearchName().toLowerCase())) {
                    newCompanyList.add(company);
                }
            }
            return newCompanyList;
        } else {
            return getCompanyList();
        }
    }

    public List<Function> getFunctionListByCompanyId(long companyId) {
        this.setSearchId(companyId);
        Dao<Function> dao = Dao.newInstance();

        List<Function> allFuntions = dao.getAll(Function.class);
        
        List<Function> newFunctionList = new ArrayList<>();
        newFunctionList.clear();
        for (Function function : allFuntions) {
            if (function != null && function.getCompanyId() == companyId) {
                newFunctionList.add(function);
            }
        }
        return newFunctionList;
    }
}
