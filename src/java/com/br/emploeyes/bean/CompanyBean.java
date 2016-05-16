package com.br.emploeyes.bean;

import com.br.emploeyes.dao.Dao;
import com.br.emploeyes.model.Company;
import com.br.emploeyes.model.Function;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CompanyBean implements Serializable {

    private Company company = new Company();
    private Long idCompany = 0L;
    private Long idRole = 0L;
    private Long idUser = 0L;

    public Long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void init() {
        if (this.idCompany > 0) {
            Dao<Company> dao = Dao.newInstance();
            this.setCompany(dao.getById(Company.class, this.idCompany));
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
        Dao<Company> dao = Dao.newInstance();
        Company company = dao.getById(Company.class, this.idCompany);

        if (company != null) {
            company.setName(formCompany.getName());
            company.setBranch(formCompany.getBranch());
            company.setDescription(formCompany.getDescription());
            company.setCountry(formCompany.getCountry());
            company.setState(formCompany.getState());
            company.setCity(formCompany.getCity());
            Company companySaved = dao.save(company);
            this.setIdCompany(companySaved.getId());
        } else {
            Company companySaved = dao.save(formCompany);
            this.setIdCompany(companySaved.getId());
        }
    }
}
