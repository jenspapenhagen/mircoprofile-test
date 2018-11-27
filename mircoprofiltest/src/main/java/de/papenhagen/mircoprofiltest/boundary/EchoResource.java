package de.papenhagen.mircoprofiltest.boundary;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jens.papenhagen
 */
@Path("echo")
@Produces(MediaType.APPLICATION_JSON)
public class EchoResource {

    @GET
    @Path("/{echo}")
    public JsonObject echo(@PathParam("echo") String param) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("echo", param);

        return builder.build();
    }

}
