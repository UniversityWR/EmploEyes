package com.br.emploeyes.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction; 
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class Dao<T> {

    private static Dao sDao = null;

    private Dao() {
    }

    public static Dao newInstance() {
        if (sDao == null) {
            sDao = new Dao();
        }
        return sDao;
    }

    public List<T> getAll(Class<T> clazz) { 
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(clazz);
            query.from(clazz);
            return entityManager.createQuery(query).getResultList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public T save(T object) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            T newObject = entityManager.merge(object);
            entityManager.flush();
            tx.commit();
            return newObject;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void delete(Class<T> clazz, long id) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            T object = entityManager.getReference(clazz, id);
            entityManager.remove(object);
            entityManager.flush();
            tx.commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public T getById(Class<T> clazz, long id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(clazz, id);
    }
}
