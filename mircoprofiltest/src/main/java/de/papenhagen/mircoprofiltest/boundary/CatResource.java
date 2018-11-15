/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.papenhagen.mircoprofiltest.boundary;

import de.papenhagen.mircoprofiltest.dao.CatDao;
import de.papenhagen.mircoprofiltest.entities.Cat;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
    public JsonObject all() {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        catDao.findAll(Cat.class).stream().forEach(c -> {
            builder.add("Id", c.getId());
            builder.add("CatName", c.getName());
        });

        return builder.build();
    }

    @POST
    public JsonObject save(Cat cat) {
        catDao.save(cat);

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("save", cat.getName());
        return builder.build();
    }

    @PUT
    @Consumes("application/json")
    public JsonObject update(Cat cat) {
        catDao.update(cat);
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("update", cat.getName());

        return builder.build();
    }

    @DELETE
    @Path("/{id}")
    public JsonObject delete(@PathParam("id") Long id) {
          JsonObjectBuilder builder = Json.createObjectBuilder();
        
        Cat cat = catDao.findById(Cat.class, id);
        if(cat != null){
             catDao.delete(cat);
             builder.add("delete", cat.getName());
        }else{
            builder.add("delete", "failed");
        }
       return builder.build();
    }

}
