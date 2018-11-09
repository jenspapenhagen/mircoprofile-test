/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.sql.DataSource;

/**
 *
 * @author jay
 */
@Startup
@Singleton
public class StartupService {

    @Resource
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println(
                    connection.getMetaData().getDatabaseProductName() + "-"
                    + connection.getCatalog()
            );
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}
