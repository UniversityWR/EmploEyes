package com.br.emploeyes.bean;

import com.br.emploeyes.model.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class UserBean {
    
    //TESTE É TESTE MESMO E VAI TOMAR NO SEU CU!
    
    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String validateLogin(User user) { 
        return "user_account?faces-redirect=true";
    }
    
    public String addRole(User user) { 
        return "role_form?faces-redirect=true";
    }
    
    public String saveUser(User user){
        return null;
    }

}
