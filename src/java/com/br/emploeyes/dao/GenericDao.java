package com.br.emploeyes.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDao<T> {

    private final Class<T> persistedClass;

    public GenericDao(Class<T> persistedClass) {
        this.persistedClass = persistedClass;
    }

    public List<T> getAll() {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(persistedClass);
            query.from(persistedClass);
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

    public void delete(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = JPAUtil.getEntityManager();
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            T object = entityManager.getReference(persistedClass, id);
            entityManager.remove(object);
            entityManager.flush();
            tx.commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public T getById(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(persistedClass, id);
    }
}
