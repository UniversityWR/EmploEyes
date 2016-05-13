package com.br.emploeyes.bean;

import com.br.emploeyes.dao.Dao;
import com.br.emploeyes.model.Employee;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class UserBean {

    private Employee user = new Employee();
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

    public void init() {
        if (this.idUser > 0) {
            Dao<Employee> dao = Dao.newInstance();
            this.setUser(dao.getById(Employee.class, this.idUser));
        }
    }

    public String validateLogin() {

        Dao<Employee> dao = Dao.newInstance();
        List<Employee> users = dao.getAll(Employee.class);
        try {
            for (Employee u : users) {
                if (u.getEmail().equals(this.getUser().getEmail())
                        && u.getPassword().equals(this.getUser().getPassword())) {

                    this.setIdUser(u.getId());
                    return "user_account?faces-redirect=true&includeViewParams=true";
                }
            }
        } catch (ClassCastException e) {

        }
        return null;
    }

    public String createEmployee() {
        return "user_account";
    }

    public String addRole(Employee user) {
        /* Remove/Clear the Managed Role from the ApplicationScoped*/
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().remove("roleBean");
        return "role_form?faces-redirect=true&includeViewParams=true";
    }

    public String saveUser(Employee user) {
        Dao<Employee> dao = Dao.newInstance();
        Employee employee = dao.getById(Employee.class, this.idUser);

        if (employee != null) {
            employee.setName(user.getName());
            employee.setYearOld(user.getYearOld());
            employee.setState(user.getState());
            employee.setCity(user.getCity());
            employee.setCountry(user.getCountry());
            employee.setEmail(user.getEmail());
            employee.setPassword(user.getPassword());

            dao.save(employee);
        }else { 
            dao.save(user);
        }
        return "index?faces-redirect=true&includeViewParams=true";
    }
}
