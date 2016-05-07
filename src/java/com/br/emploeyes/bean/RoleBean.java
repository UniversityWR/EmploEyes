package com.br.emploeyes.bean;

import com.br.emploeyes.model.Role;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
 
@ManagedBean
@RequestScoped
public class RoleBean {
    
    private Role role = new Role();

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    } 
    
    public String saveRole(Role role){
        return null;
    }
}
