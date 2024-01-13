package tdd.blogProject.blog.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import tdd.blogProject.advice.exception.ExceptionResponse;
import tdd.blogProject.advice.success.SuccessResponse;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.blog.application.port.in.BlogCreateUseCase;
import tdd.blogProject.blog.domain.BlogTitle;
import tdd.blogProject.user.domain.UserName;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static tdd.blogProject.advice.success.SuccessCodeConst.BLOG_CREATE_SUCCESS;

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
    @DisplayName("POST /api/v1/blog => (201)")
    void testCreateBlog() throws Exception {
        BlogCreateCommand command = new BlogCreateCommand(new BlogTitle("Test Blog Title"), new UserName("Test UserName"));

        SuccessResponse response = new SuccessResponse(BLOG_CREATE_SUCCESS);

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .post("/api/v1/blog")
                .then()
                .statusCode(201)
                .log().ifValidationFails(ALL)
                .body(equalTo(response.toJsonString()));
    }

/*    @Test
    @DisplayName("POST /api/v1/blog => (416)")
    void testCreateBlogNegative() throws Exception {
        BlogCreateCommand command = new BlogCreateCommand(new BlogTitle(""), new UserName("Test UserName"));

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(command))
                .when()
                .post("/api/v1/blog")
                .then()
                .statusCode(416)
                .log().ifValidationFails(ALL)
                .body("status", equalTo(416))
                .body("code", equalTo("LENGTH_REQUIRED_TITLE"))
                .body("message", equalTo("블로그 제목은 1자 이상 20자 이하의 문자열이어야 합니다."));
    }*/

}