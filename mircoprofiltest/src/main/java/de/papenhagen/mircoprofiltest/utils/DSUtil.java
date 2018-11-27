/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.utils;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.sql.DataSource;

/**
 *
 * @author jay
 */
@Named
public class DSUtil implements Serializable{

    @Resource(lookup = "java:global/jdbc/embeddedDS")
    private DataSource ds;

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

  
}
