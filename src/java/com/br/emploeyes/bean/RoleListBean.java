package com.br.emploeyes.bean;

import com.br.emploeyes.dao.GenericDao;
import com.br.emploeyes.model.Function; 
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped; 

@ManagedBean
@RequestScoped
public class RoleListBean { 
    
    public List<Function> getRoleList() {
        GenericDao<Function> dao = new GenericDao<>(Function.class);
        return dao.getAll();
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
