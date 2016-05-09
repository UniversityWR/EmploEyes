package com.br.emploeyes.bean;

import com.br.emploeyes.model.Role;
import com.br.emploeyes.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean; 
import javax.faces.context.FacesContext;
 
@ManagedBean
@ApplicationScoped
public class RoleListBean {
    
    private List<Role> roleList = new ArrayList<>();

    public List<Role> getRoleList() {
        return roleList;
    }

    public List<Role> getRoleListByUserId(long id) {
        
        List<Role> newRoleList = new ArrayList<>();
        newRoleList.clear();
        for(Role role : roleList){
            if(role.getUserId() == id){
                newRoleList.add(role);
            }
        } 
        return newRoleList;
    }  
    
    public String editRole(long id) {
        
        List<Role> newRoleList = new ArrayList<>();
        newRoleList.clear();
        for(Role role : roleList){
            if(role.getId() == id){ 
                System.out.println(role.getId());
                //Add object Role to the ApplicationScoped
                FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("roleBean.role", role);
                return "role_form?faces-redirect=true";
            }
        } 
        return "";
    }  
    
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    } 
         
    public String saveRole(Role role, User user){
        role.setUserId(user.getId());
        if(!roleList.contains(role)){
            roleList.add(role); 
        }
        return "user_account?faces-redirect=true";
    } 
}
