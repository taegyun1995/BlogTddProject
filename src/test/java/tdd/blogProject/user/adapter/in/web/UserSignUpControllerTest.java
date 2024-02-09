package tdd.blogProject.user.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import tdd.blogProject.user.application.port.in.UserSignUpCommand;
import tdd.blogProject.user.application.port.in.UserSignUpUserCase;
import tdd.blogProject.user.domain.LoginId;
import tdd.blogProject.user.domain.Nickname;
import tdd.blogProject.user.domain.Password;
import tdd.blogProject.user.domain.UserName;

import javax.swing.text.html.Option;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserSignUpControllerTest {

    ObjectMapper objectMapper;

    @MockBean
    UserSignUpUserCase sut;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("POST /api/v1/user => (201) Create")
    void positiveCaseUserSignUp() throws JsonProcessingException {
        given().
                contentType(JSON)
                .body(objectMapper.writeValueAsString(getUserSignUpCommand()))
                .when()
                .post("/api/v1/user")
                .then()
                .statusCode(CREATED.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(1)).signUp(any());
    }

    @Test
    @DisplayName("POST /api/v1/user => (400) Bad Request")
    void negativeCaseUserSignUpBadRequest() {
        given().
                contentType(JSON)
                .body(Option.class)
                .when()
                .post("/api/v1/user")
                .then()
                .statusCode(BAD_REQUEST.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).signUp(any());
    }

    @Test
    @DisplayName("POST /api/v1/user => (404) Not Found")
    void negativeCaseUserSignUpNotFound() throws JsonProcessingException {
        given().
                contentType(JSON)
                .body(objectMapper.writeValueAsString(getUserSignUpCommand()))
                .when()
                .post("/not_found")
                .then()
                .statusCode(NOT_FOUND.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).signUp(any());
    }

    @Test
    @DisplayName("POST /api/v1/user with TEXT content type => (415) Unsupported Media Type")
    void negativeCaseUserSignUpWithTextContentType() throws Exception {
        given()
                .contentType(ContentType.TEXT)
                .body(objectMapper.writeValueAsString(getUserSignUpCommand()))
                .when()
                .post("/api/v1/user")
                .then()
                .statusCode(UNSUPPORTED_MEDIA_TYPE.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).signUp(any());
    }

    @Test
    @DisplayName("POST /api/v1/user with XML content type => (415) Unsupported Media Type")
    void negativeCaseUserSignUpWithXMLContentType() throws Exception {
        given()
                .contentType(ContentType.XML)
                .body(objectMapper.writeValueAsString(getUserSignUpCommand()))
                .when()
                .post("/api/v1/user")
                .then()
                .statusCode(UNSUPPORTED_MEDIA_TYPE.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).signUp(any());
    }

    @Test
    @DisplayName("POST /api/v1/user with HTML content type => (415) Unsupported Media Type")
    void negativeCaseUserSignUpWithHTMLContentType() throws Exception {
        given()
                .contentType(ContentType.HTML)
                .body(objectMapper.writeValueAsString(getUserSignUpCommand()))
                .when()
                .post("/api/v1/user")
                .then()
                .statusCode(UNSUPPORTED_MEDIA_TYPE.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).signUp(any());
    }

    @Test
    @DisplayName("POST /api/v1/user => (500) Internal Server Error")
    void negativeCaseUserSignUpInternalServerError() throws JsonProcessingException {
        doThrow(new RuntimeException("Internal Server Error")).when(sut).signUp(any());

        given()
                .contentType(JSON)
                .body(objectMapper.writeValueAsString(getUserSignUpCommand()))
                .when()
                .post("/api/v1/user")
                .then()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(1)).signUp(any());
    }

    private UserSignUpCommand getUserSignUpCommand() {
        return new UserSignUpCommand(
                new LoginId("test id"),
                new Password("test pw"),
                new UserName("test name"),
                new Nickname("test nickname")
        );
    }

}