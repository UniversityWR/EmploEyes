package com.br.emploeyes.bean;

import com.br.emploeyes.dao.GenericDao;
import com.br.emploeyes.model.Company;
import com.br.emploeyes.model.Employee;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

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
            GenericDao<Employee> dao = new GenericDao<>(Employee.class);
            this.setUser(dao.getById(this.idUser));
        }
    }

    public String validateLogin() {

        GenericDao<Employee> dao = new GenericDao<>(Employee.class);
        List<Employee> users = dao.getAll();
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
        return "user_account?faces-redirect=true&includeViewParams=true";
    }
    
    public String login() {
        return "index?faces-redirect=true";
    }

     public String searchList() {
        return "";
    }
    
    public String saveUser(Employee user) {
        save(user); 
        return "user_account?faces-redirect=true&includeViewParams=true"; //return "index?faces-redirect=true&includeViewParams=true";
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
