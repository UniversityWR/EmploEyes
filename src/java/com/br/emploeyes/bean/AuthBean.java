package com.br.emploeyes.bean;

import com.br.emploeyes.dao.EmployeeDao;
import com.br.emploeyes.dao.GenericDao;
import com.br.emploeyes.model.Employee;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class AuthBean implements Serializable {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        EmployeeDao dao = new EmployeeDao();
        Employee user = dao.getUser(email, password);
        if (user != null) {
            externalContext.getSessionMap().put("user", user); 
                    return "user_account?faces-redirect=true&includeViewParams=true"; 
        } else {
//            ResourceBundle bundle = ResourceBundle.getBundle(
//                    "edu.bundles.MessageBundle",
//                    context.getViewRoot().getLocale());
//
//            context.addMessage(null, new FacesMessage(bundle.getString("error.loginfailed.message")));
            
            context.addMessage(null, new FacesMessage("error.loginfailed.message"));
            return "/index";
        } 
    }

    public String logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();

        return "/index?faces-redirect=true";
    }
}
