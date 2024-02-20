package org.example.api.assured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiTest {

    private static final String END_POINT = "/api/users";

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void get_ok_test() {
        given()
                .get(END_POINT)
                .then()
                .statusCode(204);
    }

    @Test
    void post_ok_test() {
        given()
                .post(END_POINT)
                .then()
                .statusCode(204);
    }

    @Test
    void login_ok_test() {
        given()
                .post(END_POINT + "/login")
                .then()
                .statusCode(204);
    }

    @Test
    void join_ok_test() {
        given()
                .post(END_POINT + "/join")
                .then()
                .statusCode(204);
    }

    @Test
    void information_entry_point_test() {
        given()
                .get(END_POINT + "/information")
                .then()
                .statusCode(418);
    }

    @Test
    void rank_entry_point_test() {
        given()
                .get(END_POINT + "/rank")
                .then()
                .statusCode(418);
    }

    @Test
    void like_enrty_point_test() {
        given()
                .get(END_POINT + "/like")
                .then()
                .statusCode(418);
    }
}