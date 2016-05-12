package com.br.emploeyes.bean;

import com.br.emploeyes.dao.Dao;
import com.br.emploeyes.model.Role;
import com.br.emploeyes.model.User;
import java.util.ArrayList;
import java.util.List; 
import javax.faces.bean.ManagedBean; 
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean
@RequestScoped
public class RoleListBean { 

    public List<Role> getRoleList() {
        Dao<Role> dao = Dao.newInstance(); 
        return dao.getAll(Role.class);
    }

    public List<Role> getRoleListByUserId(long id) {
        
        List<Role> newRoleList = new ArrayList<>();
        newRoleList.clear();
        for(Role role : getRoleList()){
            if(role.getUserId() == id){
                newRoleList.add(role);
            }
        } 
        return newRoleList;
    }  
    
    public String editRole(long id) {
        
        List<Role> newRoleList = new ArrayList<>();
        newRoleList.clear();
        for(Role role : getRoleList()){
            if(role.getId() == id){ 
                System.out.println(role.getId());
                //Add object Role to the ApplicationScoped
                FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("roleBean.role", role);
                return "role_form?faces-redirect=true";
            }
        } 
        return "";
    }   
    
    public String saveRole(Role role, User user){
        role.setUserId(user.getId());
        Dao<Role> dao = Dao.newInstance();
        dao.save(role);         
        return "user_account?faces-redirect=true";
    } 
}
