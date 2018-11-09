/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.boundary;

import de.papenhagen.mircoprofiltest.dao.CatDao;
import de.papenhagen.mircoprofiltest.entities.Cat;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * CRUD REST API for Cat´s
 *
 * @author jay
 */
@Path("cat")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatResource {

    @Inject
    private CatDao catDao;

    @GET
    public List<Cat> all() {
        return catDao.findAll();
    }

    @POST
    public void save(Cat cat) {
        catDao.save(cat);
    }

    @PUT
    @Consumes("application/json")
    public void update(Cat person) {
        catDao.update(person);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        Cat person = catDao.findById(id);
        catDao.delete(person);
    }

}
