package com.github.algeralith.resource;

import com.github.algeralith.entity.*;
import com.github.algeralith.service.*;

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

@Path("/api/albums")
@ApplicationScoped
public class AlbumResource {

    @Inject
    AlbumService albumService;

    @Inject
    ImageService ImageService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Album album) {        
        Log.infof("create() : %s", album != null ? album.toString() : "Null album.");

        // Loop through the images given and make sure the id exists.
        // This is mostly done to avoid having a stack-trace printed to console.
        // The following loop could be commented out if it is accceptable to have a trace print on every failure.
        for (Image image : album.getImages()) {
            image = ImageService.getEntity(image.getId());
            if (image == null)
                return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }

        Album savedAlbum = null;

        try {
            savedAlbum = albumService.createEntity(album);

            // Essentially, this line is just used to update the entity.
            // That was we have all the images.
            if (savedAlbum != null)
                savedAlbum = albumService.getEntity(savedAlbum.getId());
        } catch (Exception e) {
            Log.error(e);
        }

        if (savedAlbum == null) {
            Log.infof("create() : Failed to persist album.");
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
        }

        Log.infof("create() : Album succesfully persisted : %s", savedAlbum.toString());

        // Clear the album property, just to prevent a recursive nightmare.
        // If given more time, there probably is a better solution.
        // Still learning JPA.
        for (Image image : savedAlbum.getImages()) {
            image.albums.clear();
        }

        return Response.ok(savedAlbum).status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") long id) {        
        Log.infof("read() : id: %d", id);

        Album album = albumService.getEntity(id);

        Log.infof("read() : %s", album != null ? album.toString() : "Null album.");

        if (album == null) {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        } else  {
            // Clear the album property, just to prevent a recursive nightmare.
            for (Image image : album.getImages()) {
                image.albums.clear();
            }

            return Response.ok(album).status(Response.Status.OK).build();
        }
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

        boolean deleted = albumService.deleteEntity(id);

        Log.infof("delete() : id: %d : deleted? %b", id, deleted);

        if (deleted) {
            return Response.ok().status(Response.Status.OK).build();
        } else
            return Response.ok().status(Response.Status.BAD_REQUEST).build();
    }
}
