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
}
