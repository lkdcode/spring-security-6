package org.example.api.assured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostApiTest {

    private static final String END_POINT = "/domain/posts";

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void post_ok_test() {
        given()
                .post(END_POINT)
                .then()
                .statusCode(204);
    }

    @Test
    void get_ok_test() {
        given()
                .get(END_POINT + "/get")
                .then()
                .statusCode(204);
    }

    @Test
    void get_all_ok_test() {
        given()
                .get(END_POINT + "/get-all")
                .then()
                .statusCode(204);
    }

    @Test
    void delete_entry_point_test() {
        given()
                .delete(END_POINT)
                .then()
                .statusCode(418);
    }

    @Test
    void put_entry_point_test() {
        given()
                .put(END_POINT)
                .then()
                .statusCode(418);
    }
}