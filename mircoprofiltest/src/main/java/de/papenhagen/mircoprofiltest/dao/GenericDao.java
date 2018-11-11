/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.dao;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;

/**
 * GenericDao with default methodes written in Criteria api 
 * //TODO move to querydsl
 *
 * @author jay
 */
@SuppressWarnings("serial")
public class GenericDao<T> implements Serializable {

    @Inject
    protected EntityManager em;

    private Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findById(Object id) {
        if (id == null) {
            return null;
        }
        return em.find(entityClass, id);
    }

    public T findById(Object id, LockModeType lockModeType) {
        if (id == null) {
            return null;
        }
        return em.find(entityClass, id, lockModeType);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public List<T> findAll(int start, int amount) {
        javax.persistence.criteria.CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).setFirstResult(start).setMaxResults(amount).getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult().intValue();
    }

    public void remove(Object entity) {
        em.remove(entity);
    }

    public void flush() {
        em.flush();
    }

    public void clear() {
        em.clear();
    }

    public <T> List<T> nativeSqlQuery(String sqlQuery, Class<T> clazz) {
        Query query = em.createNativeQuery(sqlQuery, clazz);
        @SuppressWarnings("unchecked")
        List<T> resultList = query.getResultList();
        return resultList;
    }

    public List<?> nativeSqlQuery(String sqlQuery) {
        Query query = em.createNativeQuery(sqlQuery);
        return query.getResultList();
    }
}
