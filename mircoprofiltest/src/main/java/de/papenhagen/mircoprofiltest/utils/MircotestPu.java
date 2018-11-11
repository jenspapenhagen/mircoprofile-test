/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.utils;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * buildup the Persistence unit for this project
 *
 * @author jens.papenhagen
 */
public class MircotestPu {

    @Produces
    @PersistenceContext(unitName = "mircotest-pu")
    private EntityManager entityManager;

}
