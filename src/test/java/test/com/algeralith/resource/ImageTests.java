package test.com.algeralith.resource;

import com.github.algeralith.entity.Image;
import com.github.algeralith.resource.ImageResource;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import javax.ws.rs.core.*;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
@TestHTTPEndpoint(ImageResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImageTests {

    @Test
    @Order(1)
    public void testImageCreation() {
        // Create an image resource.
        Image image = new Image();
        image.setTitle("Title 1");
        image.setDescription("Description 1");
        
        given().contentType(MediaType.APPLICATION_JSON).body(image.toString()).when().post().then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(1),
            "title", equalTo(image.getTitle()),
            "description", equalTo(image.getDescription())
        );

        image = new Image();
        image.setTitle("Title 2");
        image.setDescription("Description 2");
        
        given().contentType(MediaType.APPLICATION_JSON).body(image.toString()).when().post().then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(2),
            "title", equalTo(image.getTitle()),
            "description", equalTo(image.getDescription())
        );

        image = new Image();
        image.setTitle("Title 3");
        image.setDescription("Description 3");
        
        given().contentType(MediaType.APPLICATION_JSON).body(image.toString()).when().post().then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(3),
            "title", equalTo(image.getTitle()),
            "description", equalTo(image.getDescription())
        );

        image = new Image();
        image.setTitle("Title 4");
        image.setDescription("Description 4");
        
        given().contentType(MediaType.APPLICATION_JSON).body(image.toString()).when().post().then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(4),
            "title", equalTo(image.getTitle()),
            "description", equalTo(image.getDescription())
        );
      }

      @Test
      @Order(2)
      public void testImageRead() {
        // Check to see if we can return all resources as expected.
        given().when().get("/1").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(1),
            "title", equalTo("Title 1"),
            "description", equalTo("Description 1")
        );

        given().when().get("/2").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(2),            
            "title", equalTo("Title 2"),
            "description", equalTo("Description 2")
        );

        given().when().get("/3").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(3),
            "title", equalTo("Title 3"),
            "description", equalTo("Description 3")
        );

        given().when().get("/4").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(4),
            "title", equalTo("Title 4"),
            "description", equalTo("Description 4")
        );
      }

      @Test
      @Order(3)
      public void testImageUpdate() {
        Image image = new Image();

        //
        image.setTitle("Updated Title 1");
        image.setDescription("Updated Description 1");
        
        given().contentType(MediaType.APPLICATION_JSON).body(image.toString()).when().put("/1").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(1),
            "title", equalTo(image.getTitle()),
            "description", equalTo(image.getDescription())
        );

        //
        image.setTitle("Updated Title 2");
        image.setDescription("Updated Description 2");
        
        given().contentType(MediaType.APPLICATION_JSON).body(image.toString()).when().put("/2").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(2),
            "title", equalTo(image.getTitle()),
            "description", equalTo(image.getDescription())
        );

        //
        image.setTitle("Updated Title 3");
        image.setDescription("Updated Description 3");
        
        given().contentType(MediaType.APPLICATION_JSON).body(image.toString()).when().put("/3").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(3),
            "title", equalTo(image.getTitle()),
            "description", equalTo(image.getDescription())
        );

        //
        image.setTitle("Updated Title 4");
        image.setDescription("Updated Description 4");
        
        given().contentType(MediaType.APPLICATION_JSON).body(image.toString()).when().put("/4").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(4),
            "title", equalTo(image.getTitle()),
            "description", equalTo(image.getDescription())
        );
      }

      @Test
      @Order(4)
      public void testImageDelete()  {
        
        // Delete the resources.
        given().when().delete("/1").then()
        .statusCode(Response.Status.OK.getStatusCode());

        given().when().delete("/2").then()
        .statusCode(Response.Status.OK.getStatusCode());

        given().when().delete("/3").then()
        .statusCode(Response.Status.OK.getStatusCode());

        given().when().delete("/4").then()
        .statusCode(Response.Status.OK.getStatusCode());

        // Verify they return as not found after deletion.
        given().when().get("/1").then()
        .statusCode(Response.Status.NOT_FOUND.getStatusCode());

        given().when().get("/2").then()
        .statusCode(Response.Status.NOT_FOUND.getStatusCode());

        given().when().get("/3").then()
        .statusCode(Response.Status.NOT_FOUND.getStatusCode());

        given().when().get("/4").then()
        .statusCode(Response.Status.NOT_FOUND.getStatusCode());
      }
}
