package com.br.emploeyes.bean;

import com.br.emploeyes.model.Role;
import static com.sun.faces.el.FacesCompositeELResolver.ELResolverChainType.Faces; 
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
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
}
