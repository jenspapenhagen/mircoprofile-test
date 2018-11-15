/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.entities;

/**
 * By implementing the interface an entity supplies the contract to load the object tree eager on demand.
 *
 * @author jens.papenhagen
 */
public interface EagerAble {

    /**
     * Should be called by any Agent if an Eager Method is used.
     */
    void fetchEager();
}
