package com.github.algeralith.resource;

import com.github.algeralith.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/api/album")
@ApplicationScoped
public class AlbumResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Image image) {
        return Response.ok().status(Response.Status.NOT_IMPLEMENTED).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read() {
        return Response.ok().status(Response.Status.NOT_IMPLEMENTED).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update() {
        return Response.ok().status(Response.Status.NOT_IMPLEMENTED).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete() {
        return Response.ok().status(Response.Status.NOT_IMPLEMENTED).build();
    }
}
