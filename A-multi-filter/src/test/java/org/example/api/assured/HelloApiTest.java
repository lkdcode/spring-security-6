package org.example.api.assured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void hello_api_test() throws Exception {
        given()
                .when()
                .post("/hello")
                .then()
                .statusCode(418);
    }

    @Test
    void hello_world_api_test() throws Exception {
        given()
                .when()
                .post("/hello/world")
                .then()
                .statusCode(418);
    }
}