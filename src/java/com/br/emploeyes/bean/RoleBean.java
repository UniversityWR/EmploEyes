package com.br.emploeyes.bean;

import com.br.emploeyes.model.Function;
import static com.sun.faces.el.FacesCompositeELResolver.ELResolverChainType.Faces; 
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean
@RequestScoped
public class RoleBean {
    
    private Function role = new Function();

    public Function getRole() {
        return role;
    }

    public void setRole(Function role) {
        this.role = role;
    }  
}
