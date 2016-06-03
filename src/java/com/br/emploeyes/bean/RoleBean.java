package com.br.emploeyes.bean;

import com.br.emploeyes.dao.GenericDao;
import com.br.emploeyes.model.Company;
import com.br.emploeyes.model.Function;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class RoleBean implements Serializable {

    private Function role = new Function();
    private Company company = new Company();
    private Long idRole = 0L;
    private Long idUser = 0L;
    private Long idCompany = 0L;
    private boolean shouldCreateCompany;

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

    public boolean isShouldCreateCompany() {
        return shouldCreateCompany;
    }

    public void setShouldCreateCompany(boolean shouldCreateCompany) {
        this.shouldCreateCompany = shouldCreateCompany;
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
            GenericDao<Function> dao = new GenericDao<>(Function.class);
            this.setRole(dao.getById(this.idRole));
            this.setIdCompany(this.getRole().getCompanyId());
            if (this.idCompany > 0) {
                this.getRole().setCompanyId(this.idCompany);
                GenericDao<Company> daocompany = new GenericDao<>(Company.class);
                this.setCompany(daocompany.getById(this.idCompany));
            }
        }
    }

    public String cancel() {
        return "user_account?faces-redirect=true&includeViewParams=true";
    }

    public String finish() {
        return "user_account?faces-redirect=true&includeViewParams=true";
    }

    public String saveRole(Function role, Long idUser, CompanyBean companyBean) {
        if (shouldCreateCompany) {
            Long saveCompanyID = companyBean.save(this.getCompany());
            role.setCompanyId(saveCompanyID);
        } else {
            role.setCompanyId(this.getIdCompany());
        }
        this.setIdUser(idUser);
        save(role);
        return "user_account?faces-redirect=true&includeViewParams=true";
    }

    public String editRole(long id) {
        this.setIdRole(id);
        return "role_form?faces-redirect=true&includeViewParams=true";
    }

    public String removeRole(long id) {
        GenericDao<Function> dao = new GenericDao<>(Function.class);
        dao.delete(id);
        return "";
    }

    private void save(Function formRole) {
        GenericDao<Function> dao = new GenericDao<>(Function.class);
        Function function = dao.getById(this.idRole);

        if (function != null) {
            function.setName(formRole.getName());
            function.setUserId(this.getIdUser());
            function.setCompanyId(formRole.getCompanyId());
            function.setStartYear(formRole.getStartYear());
            function.setCurrentJob(formRole.isCurrentJob());
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
