/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.dao;

import de.papenhagen.mircoprofiltest.entities.Cat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Default test Dao
 * @author jay
 */
@Stateless
public class CatDao {

    @PersistenceContext(unitName = "mircotest-pu")
    private EntityManager entityManager;

    public List<Cat> findAll() {
        return entityManager.createNamedQuery("Cat.findAll", Cat.class).getResultList();
    }

    public Cat findById(long id) {
        return entityManager.find(Cat.class, id);
    }
    
    public int count(){
       return entityManager.createNamedQuery("Cat.findAll", Cat.class).getResultList().size();
    }

    public void save(Cat cat) {
        entityManager.persist(cat);
    }

    public void update(Cat cat) {
        entityManager.merge(cat);
    }

    public void delete(Cat cat) {
        entityManager.remove(cat);
    }

}
