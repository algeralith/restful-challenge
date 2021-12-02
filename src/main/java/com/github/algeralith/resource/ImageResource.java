package com.github.algeralith.resource;

import com.github.algeralith.entity.*;
import com.github.algeralith.service.ImageService;

import io.quarkus.logging.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
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

@Path("/api/images")
@ApplicationScoped
public class ImageResource {

    @Inject
    ImageService imageService;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Image image) {
        Log.infof("create() : %s", image != null ? image.toString() : "Null image.");

        Image savedImage = imageService.createEntity(image);

        if (savedImage == null) {
            Log.infof("create() : Failed to persist image.");
            return Response.ok(savedImage).status(Response.Status.BAD_REQUEST).build();
        }

        Log.infof("create() : Image succesfully persisted : %s", savedImage.toString());

        // Clear images to prevent recursive nightmare.
        for(Album a : image.getAlbums()) {
            a.getImages().clear();
        }

        return Response.ok(savedImage).status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") long id) {
        Log.infof("read() : id: %d", id);

        Image image = imageService.getEntity(id);

        Log.infof("read() : %s", image != null ? image.toString() : "Null image.");

        if (image == null) {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        } else 
            // Clear images to prevent recursive nightmare.
            for(Album a : image.getAlbums()) {
                a.getImages().clear();
            }

            return Response.ok(image).status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") long id, Image image) {
        Log.infof("update() : id: %d : %s", id, image.toString());

        // Set the ID and send off to be updated.
        image.setId(id);

        image = imageService.updateEntity(image);

        Log.infof("update() : image updated : %s", image != null ? image.toString() : "Null image.");

        if (image == null)
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        else {
            // Clear images to prevent recursive nightmare.
            for(Album a : image.getAlbums()) {
                a.getImages().clear();
            }

            return Response.ok(image).status(Response.Status.OK).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        Log.infof("delete() : id: %d", id);

        boolean deleted = imageService.deleteEntity(id);

        Log.infof("delete() : id: %d : deleted? %b", id, deleted);

        if (deleted) {
            return Response.ok().status(Response.Status.OK).build();
        } else
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
    }
}