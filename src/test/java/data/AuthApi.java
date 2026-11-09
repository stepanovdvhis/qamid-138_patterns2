package data;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class AuthApi {
    private AuthApi() {
    }

    public static void createUser(RegistrationDto user) {
        given()
                .baseUri("http://localhost:9999")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }
}