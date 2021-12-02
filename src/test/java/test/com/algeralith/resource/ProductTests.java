package test.com.algeralith.resource;

import com.github.algeralith.entity.Album;
import com.github.algeralith.entity.Product;
import com.github.algeralith.resource.ProductResource;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import javax.ws.rs.core.*;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
@TestHTTPEndpoint(ProductResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(3)
public class ProductTests {

    @Test
    @Order(1)
    public void testProductCreation() {
        Product product = new Product();
        product.setName("Test Product Name");
        product.setDescription("Test Product Description");
        // product.setAlbum(new Album(5));

        given().contentType(MediaType.APPLICATION_JSON).body(product.toString()).when().post().then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(1),
            "description", equalTo(product.getDescription()),
            "name", equalTo(product.getName())
        );
    }

    @Test
    @Order(2)
    public void testProductRead() {
        given().when().get("/1").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(1),
            "description", equalTo("Test Product Description"),
            "name", equalTo("Test Product Name")
        );
    }

    @Test
    @Order(3)
    public void testProductUpdate() {
        Product product = new Product();
        product.setName("Updated Test Product Name");
        product.setDescription("Updated Test Product Description");

        given().contentType(MediaType.APPLICATION_JSON).body(product.toString()).when().put("/1").then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body("id", equalTo(1),
            "description", equalTo(product.getDescription()),
            "name", equalTo(product.getName())
        );
    }

    @Test
    @Order(4)
    public void testProductDelete()  {
        given().when().delete("/1").then()
        .statusCode(Response.Status.OK.getStatusCode());

        // Verify they return as not found after deletion.
        given().when().get("/1").then()
        .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }
}
