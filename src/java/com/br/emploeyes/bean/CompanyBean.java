package com.br.emploeyes.bean;

import com.br.emploeyes.dao.GenericDao;
import com.br.emploeyes.model.Company;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CompanyBean implements Serializable {

    private Company company = new Company();
    private Long idCompany = 0L; 
    
    public Long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
    }
 
    public void init() {
        if (this.idCompany > 0) {
            GenericDao<Company> dao = new GenericDao<>(Company.class);
            this.setCompany(dao.getById(this.idCompany));
        }
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String cancel() {
        return "user_account?faces-redirect=true&includeViewParams=true";
    }

    public Long saveCompany(Company company) {
        save(company);
        return this.getIdCompany();//"role_form?faces-redirect=true&includeViewParams=true";
    }

    public String editCompany(long id) {
        this.setIdCompany(id);
        return "company_form?faces-redirect=true&includeViewParams=true";
    }

    private void save(Company formCompany) {
        GenericDao<Company> dao = new GenericDao<>(Company.class);
        Company company = dao.getById(this.idCompany);

        if (company != null) {
            company.setName(formCompany.getName());
            company.setSite(formCompany.getSite()); 
            Company companySaved = dao.save(company);
            this.setIdCompany(companySaved.getId());
        } else {
            Company companySaved = dao.save(formCompany);
            this.setIdCompany(companySaved.getId());
        }
    }
}
