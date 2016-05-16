package com.br.emploeyes.bean;

import com.br.emploeyes.dao.Dao;
import com.br.emploeyes.model.Company;
import com.br.emploeyes.model.Employee;
import com.br.emploeyes.model.Function;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped
public class RoleBean implements Serializable {

    private Function role = new Function();
    private Company company = new Company();
    private Long idRole = 0L;
    private Long idUser = 0L;
    private Long idCompany = 0L;

    public Long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public Function getRole() {
        return role;
    }

    public void setRole(Function role) {
        this.role = role;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void init() {
        if (this.idRole > 0) {
            Dao<Function> dao = Dao.newInstance();
            this.setRole(dao.getById(Function.class, this.idRole));
            this.setIdCompany(this.getRole().getCompanyId());
            if (this.idCompany > 0) {
                this.getRole().setCompanyId(this.idCompany);
                Dao<Company> daocompany = Dao.newInstance();
                this.setCompany(daocompany.getById(Company.class, this.idCompany));
            }
        }
    }

    public String addCompany() {
        save(this.getRole());
        return "company_form?faces-redirect=true&includeViewParams=true";
    }

    public String cancel() {
        return "user_account?faces-redirect=true&includeViewParams=true";
    }

    public String finish() {
        return "user_account?faces-redirect=true&includeViewParams=true";
    }

    public String saveRole(Function role, Long idUser, CompanyBean companyBean) {
        Long saveCompanyID = companyBean.saveCompany(this.getCompany());
        this.setIdUser(idUser);
        role.setCompanyId(saveCompanyID);
        System.out.println("setCompanyId: " + saveCompanyID);
        save(role);
        return "role_form?faces-redirect=true&includeViewParams=true";
    }

    public String editRole(long id) {
        this.setIdRole(id);
        return "role_form?faces-redirect=true&includeViewParams=true";
    }

    public String removeRole(long id) {
        Dao<Function> dao = Dao.newInstance();
        dao.delete(Function.class, id);
        return "";
    }

    private void save(Function formRole) {
        Dao<Function> dao = Dao.newInstance();
        Function function = dao.getById(Function.class, this.idRole);

        if (function != null) {
            function.setName(formRole.getName());
            function.setUserId(this.getIdUser());
            function.setCompanyId(formRole.getCompanyId());
            function.setStartYear(formRole.getStartYear());
            function.setEndYear(formRole.getEndYear());
            function.setSalary(formRole.getSalary());
            function.setFeedback(formRole.getFeedback());
            dao.save(function);
        } else {
            formRole.setUserId(this.getIdUser());
            dao.save(formRole);
        }
    }
}
