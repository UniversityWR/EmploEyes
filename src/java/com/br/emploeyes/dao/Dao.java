package com.br.emploeyes.dao;
 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class Dao<T> {

    private static Dao sDao = null;

    private Dao() {
    }

    public static Dao newInstance() {
        if(sDao == null){
            sDao = new Dao();
        } 
        return sDao;
    }

    public List<T> getAll(Class<T> clazz) {
        EntityManager em = JPAUtil.getEntityManager();
        Query q = em.createQuery("select a from "+clazz.getName()+" a", clazz);
        return q.getResultList();
    }

    public T save(T object) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        T newObject = em.merge(object);
        tx.commit();
        em.close();
        return newObject;
    }

    public void delete(Class<T> clazz, long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        T object = em.getReference(clazz, id);
        em.remove(object);
        tx.commit();
        em.close();
    }

    public T getById(Class<T> clazz, long id) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.find(clazz, id);
    }
}
