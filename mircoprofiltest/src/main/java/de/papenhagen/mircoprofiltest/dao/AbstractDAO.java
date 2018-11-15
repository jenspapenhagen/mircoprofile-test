/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.dao;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;
import de.papenhagen.mircoprofiltest.entities.EagerAble;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * GenericDao with default methodes written in Criteria api //TODO move to
 * querydsl
 *
 * @author jens.papenhagen
 */
@SuppressWarnings("serial")
public abstract class AbstractDAO<Path extends EntityPath, T> implements Serializable {

    @PersistenceContext(unitName = "mircotest-pu")
    protected EntityManager em;

    private Class<T> entityClass;

    protected Path qPath;

    public EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public Path getqPath() {
        return qPath;
    }

    public void setqPath(Path qPath) {
        this.qPath = qPath;
    }

    /**
     * Returns the entity identified by Id or null if non found.
     * <p/>
     * @param <T> type of the entity
     * @param entityClass the entityClass
     * @param id the id
     * @return the entity identified by Id or null if non found.
     */
    public <T> T findById(Class<T> entityClass, Object id) {
        validate(entityClass);
        if (id == null) {
            return null;
        }
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Returns the entity identified by Id or null if non found. This is the
     * eager implementation. If the Entity implements {@link EagerAble}, the
     * method fetchEager is called in the transaction.
     * <p/>
     * @param <T> type of the entity
     * @param entityClass the entityClass
     * @param id the id
     * @return the entity identified by Id or null if non found.
     */
    public <T> T findByIdEager(Class<T> entityClass, Object id) {
        return optionalFetchEager(findById(entityClass, id));
    }

    /**
     * Returns the entity identified by Id and locks it by lockMode or null if
     * non found.
     * <p/>
     * @param <T> type of the entity
     * @param entityClass the entityClass
     * @param id the id
     * @param lockModeType the lockMode to use
     * @return the entity identified by Id or null if non found.
     */
    public <T> T findById(Class<T> entityClass, Object id, LockModeType lockModeType) {
        validate(entityClass);
        if (id == null) {
            return null;
        }
        return getEntityManager().find(entityClass, id, lockModeType);
    }

    /**
     * Returns the entity identified by Id and locks it by lockMode or null if
     * non found. This is the eager implementation. If the Entity implements
     * {@link EagerAble}, the method fetchEager is called in the transaction.
     * <p/>
     * @param <T> type of the entity
     * @param entityClass the entityClass
     * @param id the id
     * @param lockModeType the lockMode to use
     * @return the entity identified by Id or null if non found.
     */
    public <T> T findByIdEager(Class<T> entityClass, Object id, LockModeType lockModeType) {
        return optionalFetchEager(findById(entityClass, id, lockModeType));
    }

    /**
     * Returns all entities of the entityClass or an empty list.
     * <p/>
     * @param <T> the type of the entities
     * @param entityClass the entityClass
     * @return all entities of the entityClass or an empty list.
     */
    public <T> List<T> findAll(Class<T> entityClass) {
        validate(entityClass);

        JPAQuery query = new JPAQuery(em);
        return query.from(qPath).fetch();
    }

    /**
     * Returns all entities of the entityClass or an empty list. This is the
     * eager implementation. If the Entity implements {@link EagerAble}, the
     * method fetchEager is called in the transaction.
     * <p/>
     * @param <T> the type of the entities
     * @param entityClass the entityClass
     * @return all entities of the entityClass or an empty list.
     */
    public <T> List<T> findAllEager(Class<T> entityClass) {
        return optionalFetchEager(findAll(entityClass));
    }

    /**
     * Returns all entities of the entityClass in the supplied interval or an
     * empty list.
     * <p/>
     * @param <T> the type of the entities
     * @param entityClass the entityClass
     * @param start the start of the full result to begin the list with.
     * @param amount the amount of elemets to return at max.
     * @return all entities of the entityClass or an empty list.
     */
    public <T> List<T> findAll(Class<T> entityClass, int start, int amount) {
        validate(entityClass);

        JPAQuery query = new JPAQuery(em);
        return query.from(qPath).fetch().subList(start, amount);
    }

    /**
     * Returns all entities of the entityClass in the supplied interval or an
     * empty list. This is the eager implementation. If the Entity implements
     * {@link EagerAble}, the method fetchEager is called in the transaction.
     * <p/>
     * @param <T> the type of the entities
     * @param entityClass the entityClass
     * @param start the start of the full result to begin the list with.
     * @param amount the amount of elemets to return at max.
     * @return all entities of the entityClass or an empty list.
     */
    public <T> List<T> findAllEager(Class<T> entityClass, int start, int amount) {
        return optionalFetchEager(findAll(entityClass, start, amount));
    }

    public <T> long count(Class<T> entityClass) {
        validate(entityClass);

        return Long.valueOf(findAll(entityClass).size());
    }

    private void validate(Class<?> clazz) {
        Objects.requireNonNull(clazz, "Supplied entityClass is null");
        Objects.requireNonNull(getEntityManager(), "Supplied EntityManager is null");
    }

    protected final <T> T optionalFetchEager(T entity) {
        if (entity == null) {
            return null;
        }
        if (entity instanceof EagerAble) {
            ((EagerAble) entity).fetchEager();
        }
        return entity;
    }

    protected final <T> List<T> optionalFetchEager(List<T> entities) {
        if (entities == null) {
            return null;
        }
        if (entities.isEmpty()) {
            return entities;
        }
        if (!(entities.get(0) instanceof EagerAble)) {
            return entities;
        }
        entities.forEach((entity) -> {
            ((EagerAble) entity).fetchEager();
        });
        return entities;
    }

    protected final <T> Set<T> optionalFetchEager(Set<T> entities) {
        if (entities == null) {
            return null;
        }
        if (entities.isEmpty()) {
            return entities;
        }
        if (!(entities.iterator().next() instanceof EagerAble)) {
            return entities;
        }
        entities.forEach((entity) -> {
            ((EagerAble) entity).fetchEager();
        });
        return entities;
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
