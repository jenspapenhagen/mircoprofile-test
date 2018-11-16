/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.dao;

import de.papenhagen.mircoprofiltest.entities.Cat;
import de.papenhagen.mircoprofiltest.entities.QCat;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 * Default test Dao
 *
 * @author jens.papenhagen
 */
@ApplicationScoped
@Named
public class CatDao extends AbstractDAO<QCat, Cat> implements Serializable {

    @Inject
    private EntityManager em;

    public CatDao(Class<Cat> entityClass, QCat qPath) {
        super(entityClass, qPath);
    }

    public CatDao() {
        super(Cat.class, QCat.cat);
    }

    public CatDao(EntityManager em) {
        this();
        this.em = em;
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
