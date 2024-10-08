package tests;

import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.apache.commons.lang3.StringUtils;
import static io.restassured.RestAssured.*;
import static io.qameta.allure.Allure.step;
import io.qameta.allure.Story;
import io.qameta.allure.Severity;
import models.*;
import static specs.ReqresApiSpec.*;
import static utils.TestData.getDataForRegistration;
import static utils.TestData.getUserData;
import java.util.Collections;

@Story("Methods of the ReqRes API.")
public class ReqresApiTests extends TestBase {

    @Test
    @DisplayName("Create a user.")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("ReqRes")
    void createUserTest() {

        CreateUpdateUserRequestModel authBody = getUserData();

        UserResponseModel response = step("Make a request to create a user.", () ->
        given()
            .spec(userOperationsRequestSpec)
            .body(authBody)
        .when()
            .post("/users")
        .then()
            .spec(userOperationsResponseSpec)
            .statusCode(201)
            .extract().as(UserResponseModel.class));

        step("Verify that the user was created with the previously passed data.", () -> {
            assertEquals(authBody.getName(), response.getName());
            assertEquals(authBody.getJob(), response.getJob());
            assertThat(response.getId(), instanceOf(String.class));
            assertThat(response.getCreatedAt(), notNullValue());
        });

    }

    @Test
    @DisplayName("Get a user's data.")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ReqRes")
    void getSingleUserTest() {

        // userId is hardcoded because the API does not support full-value CRUD operations.
         int userId = 2;

        GetUserResponseModel response = step("Make a request to get a user's data", () ->
        given()
            .spec(userOperationsRequestSpec)
        .when()
            .get("/users/" + userId)
        .then()
            .spec(userOperationsResponseSpec)
            .statusCode(200)
            .extract().as(GetUserResponseModel.class));

        step("Perform validation of the method response", () -> {
            assertThat(response.getData(), hasKey("id"));
            assertThat(response.getSupport(), hasKey("url"));
        });

    }

    @Test
    @DisplayName("Update a user's data.")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ReqRes")
    void updateUserTest() {

        CreateUpdateUserRequestModel authBody = getUserData();

        // userId is hardcoded because the API does not support full-value CRUD operations.
        int userId = 2;

        UserResponseModel response = step("Update user's name and job request", () ->
        given()
            .spec(userOperationsRequestSpec)
            .body(authBody)
        .when()
            .put("/users/" + userId)
        .then()
            .spec(userOperationsResponseSpec)
            .statusCode(200)
            .extract().as(UserResponseModel.class));

        step("Verify that the user was updated with the previously passed data.", () -> {
            assertEquals(authBody.getName(), response.getName());
            assertEquals(authBody.getJob(), response.getJob());
        });

    }

    @Test
    @DisplayName("Register a new user.")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("ReqRes")
    void registerUserTest() {

        RegisterUserRequestModel regUserBody = getDataForRegistration();

        RegisterUserResponseModel response = step("Register user invoking the /register method", () ->
        given()
            .spec(userOperationsRequestSpec)
            .body(regUserBody)
        .when()
            .post("/register")
        .then()
            .spec(userOperationsResponseSpec)
            .statusCode(200)
            .extract().as(RegisterUserResponseModel.class));

        step("Check that the user was successfully registered.", () -> {
            assertThat(response.getId(), instanceOf(Integer.class));
            assertThat(response.getToken(), notNullValue());
            assertTrue(StringUtils.isAlphanumeric(response.getToken()));
        });

    }

    @Test
    @DisplayName("When the user does not exist in our system.")
    @Severity(SeverityLevel.MINOR)
    @Tag("ReqRes")
    void userDoesNotExistTest() {

        // This is the hardcoded usedId for the user which does not exist in the system.
        int unexistedUserId = 23;

        Object response = step("Perform a query for a non-existent user.", () ->
        given()
            .spec(userOperationsRequestSpec)
        .when()
            .get("/users/" + unexistedUserId)
        .then()
            .spec(userOperationsResponseSpec)
            .statusCode(404)
            .extract().as(Object.class));

        step("Check that the response is empty.", () -> {
            assertThat(response, equalTo(Collections.emptyMap()));
        });

    }

}