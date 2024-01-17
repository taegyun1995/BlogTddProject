package tdd.blogProject.blog.adapter.in.web;

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
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.blog.application.port.in.BlogCreateUseCase;
import tdd.blogProject.blog.domain.BlogTitle;
import tdd.blogProject.user.domain.UserName;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BlogCreateControllerTest {

    ObjectMapper objectMapper;

    @MockBean
    BlogCreateUseCase sut;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("POST /api/v1/blog => (201) Created")
    void testCreateBlog() throws Exception {
        BlogCreateCommand command = new BlogCreateCommand(new BlogTitle("Test Blog Title"), new UserName("Test UserName"));

        given()
                .contentType(JSON)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .post("/api/v1/blog")
                .then()
                .statusCode(CREATED.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(1)).createBlog(any());
    }

    @Test
    @DisplayName("POST /api/v1/blog => (400) Bad Request")
    public void testCreateBlogBadRequest() {
        given()
                .contentType(JSON)
                .body(Optional.class)
                .when()
                .post("/api/v1/blog")
                .then()
                .statusCode(BAD_REQUEST.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).createBlog(any());
    }

    @Test
    @DisplayName("POST /not_found => (404) Not Found")
    public void testCreateBlogNotFound() throws JsonProcessingException {
        BlogCreateCommand command = new BlogCreateCommand(new BlogTitle("Test Blog Title"), new UserName("Test UserName"));

        given()
                .contentType(JSON)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .post("/not_found")
                .then()
                .statusCode(NOT_FOUND.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).createBlog(any());
    }

    @Test
    @DisplayName("POST /api/v1/blog with TEXT content type => (415) Unsupported Media Type")
    void testCreateBlogWithTextContentType() throws Exception {
        BlogCreateCommand command = new BlogCreateCommand(new BlogTitle("Test Blog Title"), new UserName("Test UserName"));

        given()
                .contentType(ContentType.TEXT)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .post("/api/v1/blog")
                .then()
                .statusCode(UNSUPPORTED_MEDIA_TYPE.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).createBlog(any());
    }

    @Test
    @DisplayName("POST /api/v1/blog with XML content type => (415) Unsupported Media Type")
    void testCreateBlogWithXmlContentType() throws Exception {
        BlogCreateCommand command = new BlogCreateCommand(new BlogTitle("Test Blog Title"), new UserName("Test UserName"));

        given()
                .contentType(ContentType.XML)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .post("/api/v1/blog")
                .then()
                .statusCode(UNSUPPORTED_MEDIA_TYPE.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).createBlog(any());
    }

    @Test
    @DisplayName("POST /api/v1/blog with HTML content type => (415) Unsupported Media Type")
    void testCreateBlogWithHtmlContentType() throws Exception {
        BlogCreateCommand command = new BlogCreateCommand(new BlogTitle("Test Blog Title"), new UserName("Test UserName"));

        given()
                .contentType(ContentType.HTML)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .post("/api/v1/blog")
                .then()
                .statusCode(UNSUPPORTED_MEDIA_TYPE.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).createBlog(any());
    }


    @Test
    @DisplayName("POST /api/v1/blog => (500) Internal Server Error")
    public void testCreateBlogInternalServerError() throws JsonProcessingException {
        BlogCreateCommand command = new BlogCreateCommand(new BlogTitle("Test Blog Title"), new UserName("Test UserName"));

        doThrow(new RuntimeException("Internal Server Error")).when(sut).createBlog(any());

        given()
                .contentType(JSON)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .post("/api/v1/blog")
                .then()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(1)).createBlog(any());
    }

}