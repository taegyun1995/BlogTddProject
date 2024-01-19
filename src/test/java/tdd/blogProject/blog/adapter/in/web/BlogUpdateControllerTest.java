package tdd.blogProject.blog.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import tdd.blogProject.blog.application.port.in.BlogUpdateCommand;
import tdd.blogProject.blog.application.port.in.BlogUpdateUseCase;
import tdd.blogProject.blog.domain.BlogTitle;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BlogUpdateControllerTest {

    ObjectMapper objectMapper;

    @MockBean
    BlogUpdateUseCase sut;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("PATCH /api/v1/blog => (200) Ok")
    void positiveUpdateBlogTitle() throws Exception {
        Long blogId = 1L;
        BlogUpdateCommand command = new BlogUpdateCommand(new BlogTitle("Test Blog Title"));

        given()
                .contentType(JSON)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .patch("/api/v1/blog/{blogId}", blogId)
                .then()
                .statusCode(OK.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(1)).updateBlogTitle(any(), any());
    }

    @Test
    @DisplayName("PATCH /api/v1/blog => (400) Bad Request - body empty")
    void negativeUpdateBlogTitleNullCommand() {
        Long blogId = 1L;

        given()
                .contentType(JSON)
                .body(Optional.class)
                .when()
                .patch("/api/v1/blog/{blogId}", blogId)
                .then()
                .statusCode(BAD_REQUEST.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).updateBlogTitle(any(), any());
    }

    @Test
    @DisplayName("PATCH /api/v1/blog => (400) Bad Request - Invalid Parameter")
    void negativeUpdateBlogTitleInvalidParameter() {
        String invalidBlogId = "invalidId";
        BlogUpdateCommand command = new BlogUpdateCommand(new BlogTitle("Test Blog Title"));

        given()
                .contentType(JSON)
                .body(command)
                .when()
                .patch("/api/v1/blog/{blogId}", invalidBlogId)
                .then()
                .statusCode(BAD_REQUEST.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).updateBlogTitle(any(), any());
    }

    @Test
    @DisplayName("PATCH /api/v1/blog => (404) Not Found")
    void negativeUpdateBlogTitleNotFound() {
        BlogUpdateCommand command = new BlogUpdateCommand(new BlogTitle("Test Blog Title"));

        given()
                .contentType(JSON)
                .body(command)
                .when()
                .patch("/not_found")
                .then()
                .statusCode(NOT_FOUND.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).updateBlogTitle(any(), any());
    }

    @Test
    @DisplayName("PATCH /api/v1/blog with TEXT content type => (415) Unsupported Media Type")
    void negativeUpdateBlogTitleTextContentType() throws Exception {
        Long blogId = 1L;
        BlogUpdateCommand command = new BlogUpdateCommand(new BlogTitle("Test Blog Title"));

        given()
                .contentType(TEXT)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .patch("/api/v1/blog/{blogId}", blogId)
                .then()
                .statusCode(UNSUPPORTED_MEDIA_TYPE.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).updateBlogTitle(any(), any());
    }

    @Test
    @DisplayName("PATCH /api/v1/blog with XML content type => (415) Unsupported Media Type")
    void negativeUpdateBlogTitleXmlContentType() throws Exception {
        Long blogId = 1L;
        BlogUpdateCommand command = new BlogUpdateCommand(new BlogTitle("Test Blog Title"));

        given()
                .contentType(XML)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .patch("/api/v1/blog/{blogId}", blogId)
                .then()
                .statusCode(UNSUPPORTED_MEDIA_TYPE.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).updateBlogTitle(any(), any());
    }

    @Test
    @DisplayName("PATCH /api/v1/blog with HTML content type => (415) Unsupported Media Type")
    void negativeUpdateBlogTitleHtmlContentType() throws Exception {
        Long blogId = 1L;
        BlogUpdateCommand command = new BlogUpdateCommand(new BlogTitle("Test Blog Title"));

        given()
                .contentType(HTML)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .patch("/api/v1/blog/{blogId}", blogId)
                .then()
                .statusCode(UNSUPPORTED_MEDIA_TYPE.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(0)).updateBlogTitle(any(), any());
    }

    @Test
    @DisplayName("POST /api/v1/blog => (500) Internal Server Error")
    public void testCreateBlogInternalServerError() throws JsonProcessingException {
        Long blogId = 1L;
        BlogUpdateCommand command = new BlogUpdateCommand(new BlogTitle("Test Blog Title"));

        doThrow(new RuntimeException("Internal Server Error")).when(sut).updateBlogTitle(any(), any());

        given()
                .contentType(JSON)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .patch("/api/v1/blog/{blogId}", blogId)
                .then()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .log().ifValidationFails(ALL);

        verify(sut, times(1)).updateBlogTitle(any(), any());
    }

}