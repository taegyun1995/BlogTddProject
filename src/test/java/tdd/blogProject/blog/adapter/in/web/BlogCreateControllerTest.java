package tdd.blogProject.blog.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BlogCreateControllerTest {

    @MockBean
    private BlogCreateUseCase useCase;

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
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

        verify(useCase, times(1)).createBlog(any());
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

        verify(useCase, times(0)).createBlog(any());
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

        verify(useCase, times(0)).createBlog(any());
    }

}