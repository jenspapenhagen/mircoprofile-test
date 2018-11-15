/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.dao;

import de.papenhagen.mircoprofiltest.entities.Cat;
import de.papenhagen.mircoprofiltest.entities.QCat;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * Default test Dao
 *
 * @author jens.papenhagen
 */
@Named
@Stateless
@Transactional
public class CatDao extends AbstractDAO<QCat, Cat> implements Serializable {

    public CatDao() {
        super();
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
