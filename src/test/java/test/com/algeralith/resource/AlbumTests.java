package test.com.algeralith.resource;

import com.github.algeralith.entity.Album;
import com.github.algeralith.resource.AlbumResource;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import javax.ws.rs.core.*;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(AlbumResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(2)
public class AlbumTests {

    @Test
    @Order(1)
    public void testAlbumCreation() {
        Album album = new Album();
        album.setDescription("Test Album Description");

        given().contentType(MediaType.APPLICATION_JSON).body(album.toString()).when().post().then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(1),
            "description", equalTo(album.getDescription())
        );
    }
    
    @Test
    @Order(2)
    public void testAlbumRead() {
        given().when().get("/1").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(1),
            "description", equalTo("Test Album Description")
        );
    }

    @Test
    @Order(3)
    public void testAlbumUpdate() {
        Album album = new Album();
        album.setDescription("Updated Album Description");

        given().contentType(MediaType.APPLICATION_JSON).body(album.toString()).when().put("/1").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(1),
            "description", equalTo(album.getDescription())
        );
    }

    @Test
    @Order(4)
    public void testAlbumDelete()  {
        given().when().delete("/1").then()
        .statusCode(Response.Status.OK.getStatusCode());

        // Verify they return as not found after deletion.
        given().when().get("/1").then()
        .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }
}
