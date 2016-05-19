package com.br.emploeyes.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf = null;

    public static void init() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("EmploEyesPU");
        }
    }

    public static void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
