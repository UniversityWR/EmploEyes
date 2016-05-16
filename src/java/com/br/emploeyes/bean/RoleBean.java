package com.br.emploeyes.bean;

import com.br.emploeyes.dao.Dao;
import com.br.emploeyes.model.Employee;
import com.br.emploeyes.model.Function;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped
public class RoleBean {

    private Function role = new Function();
    private Long idRole = 0L;
    private Long idUser = 0L;

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

    public void init() {
        if (this.idRole > 0) {
            Dao<Function> dao = Dao.newInstance();
            this.setRole(dao.getById(Function.class, this.idRole));
        }
    }

    public String saveRole(Function role, Long idUser) {
        System.out.println("IdUser: " + idUser);
        System.out.println("IdUser2: " + this.getIdUser());
        this.setIdUser(idUser);
        save(role);
        return "user_account?faces-redirect=true&includeViewParams=true";
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
