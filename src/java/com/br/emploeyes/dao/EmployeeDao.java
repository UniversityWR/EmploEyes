package com.br.emploeyes.dao;

import com.br.emploeyes.model.Employee;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EmployeeDao extends GenericDao<Employee> {

    public EmployeeDao() {
        super(Employee.class);
    }

    public Employee getUser(String email, String password) {
        EntityManager entityManager = null;

        try {
            entityManager = JPAUtil.getEntityManager();

            Query q = entityManager.createQuery("from Usuario a WHERE a.email=:email AND a.password=FUNC('sha1', :password)", Employee.class);
            q.setParameter("email", email);
            q.setParameter("password", password);

            return (Employee) q.getSingleResult();
        } catch (Exception ex) {
            // error loading user. return null
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
