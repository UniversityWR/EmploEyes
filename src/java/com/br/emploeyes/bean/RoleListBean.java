package com.br.emploeyes.bean;

import com.br.emploeyes.dao.Dao;
import com.br.emploeyes.model.Function;
import com.br.emploeyes.model.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class RoleListBean {

    public List<Function> getRoleList() {
        Dao<Function> dao = Dao.newInstance();
        return dao.getAll(Function.class);
    }

    public List<Function> getRoleListByUserId(long id) {

        List<Function> newRoleList = new ArrayList<>();
        newRoleList.clear();
        try {
            for (Function role : getRoleList()) {
                if (role.getUserId() == id) {
                    newRoleList.add(role);
                }
            }
        } catch (ClassCastException e) {

        }
        return newRoleList;
    }

    public String editRole(long id) {

        List<Function> newRoleList = new ArrayList<>();
        newRoleList.clear();
        for (Function role : getRoleList()) {
            if (role.getId() == id) {
                System.out.println(role.getId());
                //Add object Role to the ApplicationScoped
                FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("roleBean.role", role);
                return "role_form?faces-redirect=true";
            }
        }
        return "";
    }

    public String saveRole(Function role, Employee user) {
        role.setUserId(user.getId());
        Dao<Function> dao = Dao.newInstance();
        dao.save(role);
        return "user_account??faces-redirect=true&includeViewParams=true";
    }
}
