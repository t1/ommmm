package com.github.t1.webresource;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/persons")
@Stateless
public class PersonExtension extends PersonWebResource {
    @PersistenceContext
    private EntityManager em;

    @GET
    @Path("/extension")
    public Response getPersonExtension() {
        return Response.ok("hello extension").build();
    }
}
