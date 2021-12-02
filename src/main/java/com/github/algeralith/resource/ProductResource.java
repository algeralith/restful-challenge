package com.github.algeralith.resource;

import com.github.algeralith.entity.*;
import com.github.algeralith.service.ProductService;

import io.quarkus.logging.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/api/products")
@ApplicationScoped
public class ProductResource {

    @Inject
    ProductService productService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Product product) {        
        Log.infof("create() : %s", product != null ? product.toString() : "Null product.");

        Product savedProduct = productService.createEntity(product);

        if (savedProduct == null) {
            Log.infof("create() : Failed to persist product.");
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }

        Log.infof("create() : product succesfully persisted : %s", savedProduct.toString());

        return Response.ok(savedProduct).status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") long id) {
        Log.infof("read() : id: %d", id);

        Product product = productService.getEntity(id);

        Log.infof("read() : %s", product != null ? product.toString() : "Null product.");

        if (product == null) {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        } else 
            return Response.ok(product).status(Response.Status.OK).build();
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
    public Response delete(@PathParam("id") long id) {
        Log.infof("delete() : id: %d", id);

        boolean deleted = productService.deleteEntity(id);

        Log.infof("delete() : id: %d : deleted? %b", id, deleted);

        if (deleted) {
            return Response.ok().status(Response.Status.OK).build();
        } else
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
    }
}
