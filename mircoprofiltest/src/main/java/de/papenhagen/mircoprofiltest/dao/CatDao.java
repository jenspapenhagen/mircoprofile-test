/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.dao;

import de.papenhagen.mircoprofiltest.entities.Cat;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Default test Dao
 *
 * @author jay
 */
@Named
@Stateless
public class CatDao extends GenericDao<Cat> {

    @PersistenceContext(unitName = "mircotest-pu")
    private EntityManager em;

    public CatDao() {
        super(Cat.class);
    }

    public CatDao(EntityManager em) {
        this();
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public Cat findById(long id) {
        return em.find(Cat.class, id);
    }

    public void save(Cat cat) {
        em.persist(cat);
    }

    public void update(Cat cat) {
        em.merge(cat);
    }

    public void delete(Cat cat) {
        em.remove(cat);
    }

 
}
