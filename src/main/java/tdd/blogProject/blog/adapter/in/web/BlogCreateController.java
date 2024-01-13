package tdd.blogProject.blog.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdd.blogProject.advice.success.SuccessResponse;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.blog.application.port.in.BlogCreateUseCase;

import static org.springframework.http.HttpStatus.CREATED;
import static tdd.blogProject.advice.success.SuccessCodeConst.BLOG_CREATE_SUCCESS;

@RestController
@RequestMapping("/api/v1")
public class BlogCreateController {

    private BlogCreateUseCase useCase;

    public BlogCreateController(BlogCreateUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/blog")
    public ResponseEntity<SuccessResponse> createBlog(
            @RequestBody BlogCreateCommand command
    ) {
        useCase.createBlog(command);
        return ResponseEntity.status(CREATED).body(new SuccessResponse(BLOG_CREATE_SUCCESS));
    }

}