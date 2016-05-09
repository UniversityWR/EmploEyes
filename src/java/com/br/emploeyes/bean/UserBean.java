package com.br.emploeyes.bean;

import com.br.emploeyes.model.User;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@ApplicationScoped
public class UserBean { 
    
    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String validateLogin(User user) { 
        return "user_account";
    }
    
    public String addRole(User user) {  
        
        /* Remove/Clear the Managed Role from the ApplicationScoped*/
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().remove("roleBean");
        return "role_form?faces-redirect=true";
    }
    
    public String saveUser(User user){
        return null;
    }

}
