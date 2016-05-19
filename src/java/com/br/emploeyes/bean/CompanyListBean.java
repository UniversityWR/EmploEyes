package com.br.emploeyes.bean;

import com.br.emploeyes.dao.GenericDao;
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
        GenericDao<Company> dao = new GenericDao<>(Company.class);
        return dao.getAll();
    }

    public Company getCompanyById(Long id) {
        GenericDao<Company> dao = new GenericDao<>(Company.class);
        if (id == null) {
            return null;
        }
        return dao.getById(id);
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
        GenericDao<Function> dao = new GenericDao<>(Function.class);

        List<Function> allFuntions = dao.getAll();

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
