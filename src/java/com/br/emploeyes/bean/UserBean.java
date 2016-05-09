package com.br.emploeyes.bean;

import com.br.emploeyes.model.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
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
        return "role_form";
    }
    
    public String saveUser(User user){
        return null;
    }

}
