/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import de.papenhagen.mircoprofiltest.utils.DSUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.Ignore;

/**
 *
 * @author jay
 */
public class DSUtilTest {

    @Inject
    private DSUtil dsutil;   

    /**
     * testing the datasource
     */
    @Ignore
    @Test
    public void testDs() {
        DataSource ds = dsutil.getDs();
        assertThat(ds).as("datasoruce is null").isNotNull();
        try {
            Connection conn = ds.getConnection();
            PreparedStatement stmt = conn.prepareStatement("Select count(*) from Cat");
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR" + ex.getMessage());
        }
        
    }
}
