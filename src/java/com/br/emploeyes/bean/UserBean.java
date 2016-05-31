package com.br.emploeyes.bean;

import com.br.emploeyes.dao.EmployeeDao;
import com.br.emploeyes.dao.GenericDao;
import com.br.emploeyes.model.Employee;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

    private Employee user = new Employee();
    private String email;
    private String password;
    private Long idUser = 0L;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }

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

    public void init() {
        if (this.idUser > 0) {
            GenericDao<Employee> dao = new GenericDao<>(Employee.class);
            this.setUser(dao.getById(this.idUser));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            Employee userSession = (Employee) externalContext.getSessionMap().get("user");
            if (userSession != null) {
                this.setUser(userSession);
                this.setIdUser(userSession.getId());
            }
        }
    }

    public boolean isProfileAdmin() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Employee user = (Employee) externalContext.getSessionMap().get("user");
        if (user != null) {
            return user.getProfile() == Employee.Profile.ADMIN;
        }
        return false;
    }

    public String login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        Employee userSession = (Employee) externalContext.getSessionMap().get("user");
        if (userSession != null) {
            this.setIdUser(userSession.getId());
            return "user_account?faces-redirect=true&includeViewParams=true";
        } else {
            EmployeeDao dao = new EmployeeDao();
            Employee userDB = dao.getUser(email, password);
            if (userDB != null) {
                externalContext.getSessionMap().put("user", userDB);
                this.setIdUser(userDB.getId());
                return "user_account?faces-redirect=true&includeViewParams=true";
            } else {
                ResourceBundle bundle = ResourceBundle.getBundle(
                        "com.br.emploeyes.bundles.MessageBundle",
                        context.getViewRoot().getLocale());

                context.addMessage(null, new FacesMessage(bundle.getString("error.loginfailed.message"))); 
                return "/index";
            }
        }
    }

    public String logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();

        return "/index?faces-redirect=true";
    }

    public String createEmployee() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().put("user", null);
        return "user_account?faces-redirect=true&includeViewParams=true";
    }

    public String loginPage() {
        return "index?faces-redirect=true";
    }

    public String searchList() {
        return "list_companies?faces-redirect=true";
    }

    public String saveUser(Employee user) {
        save(user);

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().put("user", user);
        return "user_account?faces-redirect=true&includeViewParams=true";
    }

    public String addRole(Employee user) {
        save(user);
        this.setIdUser(idUser);
        return "role_form?faces-redirect=true&includeViewParams=true";
    }

    private void save(Employee user) {
        GenericDao<Employee> dao = new GenericDao<>(Employee.class);
        Employee employee = dao.getById(this.idUser);

        if (employee != null) {
            employee.setName(user.getName());
            employee.setYearOld(user.getYearOld());
            employee.setState(user.getState());
            employee.setCity(user.getCity());
            employee.setCountry(user.getCountry());
            employee.setEmail(user.getEmail());
            employee.setPassword(user.getPassword());

            Employee newEmployee = dao.save(employee);
            this.setIdUser(newEmployee.getId());
        } else {
            Employee newEmployee = dao.save(user);
            this.setIdUser(newEmployee.getId());
        }
    }
}
