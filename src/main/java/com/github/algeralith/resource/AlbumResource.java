package com.github.algeralith.resource;

import com.github.algeralith.entity.Album;
import com.github.algeralith.service.AlbumService;

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Album album) {        
        Log.infof("create() : %s", album != null ? album.toString() : "Null album.");

        Album savedAlbum = albumService.createEntity(album);

        if (savedAlbum == null) {
            Log.infof("create() : Failed to persist album.");
            return Response.ok(savedAlbum).status(Response.Status.BAD_REQUEST).build();
        }

        Log.infof("create() : Album succesfully persisted : %s", savedAlbum.toString());

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
        } else 
            return Response.ok(album).status(Response.Status.OK).build();
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
