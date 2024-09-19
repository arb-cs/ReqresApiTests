package tests;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import java.util.Collections;

public class ReqresApiTests extends TestBase {

    int userId = 2;

    @Test
    void createUserTest() {
        String createUserPayload = """
                {
                    "name": "morpheus",
                    "job": "leader"
                }
                """;

        given()
                .body(createUserPayload)
                .contentType(JSON)
                .log().uri()
                .log().body()

                .when()
                    .post("/users")

                .then()
                    .log().status()
                    .log().body()
                    .statusCode(201)
                    .body("name", is("morpheus"))
                    .body("job", is("leader"))
                    .body("id", instanceOf(String.class))
                    .body("createdAt", notNullValue());
    }

    @Test
    void getSingleUserTest() {

        given()
                .log().uri()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .log().body()
                .body("data", hasKey("id"))
                .body("support", hasKey("url"));
    }

    @Test
    void updateUserTest() {
        String updateUserPayload = """
                {
                    "name": "morpheus",
                    "job": "zion resident"
                }
                """;

        given()
                .body(updateUserPayload)
                .contentType(JSON)
                .log().uri()
                .log().body()
                .when()
                    .put("/users/" + userId)
                .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .body("job", is("zion resident"));
    }

    @Test
    void registerUserTest() {
        String registerUserPayload = """
                {
                    "email": "eve.holt@reqres.in",
                    "password": "pistol"
                }
                """;

        given()
                .body(registerUserPayload)
                .contentType(JSON)
                .log().uri()
                .log().body()

                .when()
                    .post("/register")

                .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .body("id", instanceOf(Integer.class))
                    .body("token", notNullValue());
    }

    @Test
    void userDoesNotExistTest() {
        int userId = 23;
        given()
                .log().uri()
                .get("/users/" + userId)
                .then()
                .log().body()
                .statusCode(404)
                .body("", equalTo(Collections.emptyMap()));
    }

}