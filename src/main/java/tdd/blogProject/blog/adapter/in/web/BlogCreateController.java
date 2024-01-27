package tdd.blogProject.blog.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.blog.application.port.in.BlogCreateUseCase;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1")
public class BlogCreateController {

    private BlogCreateUseCase useCase;

    public BlogCreateController(BlogCreateUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/blog")
    public ResponseEntity<BlogCreateResponse> createBlog(
            @RequestBody BlogCreateCommand command
    ) {
        BlogCreateResponse response = useCase.createBlog(command);
        return ResponseEntity.status(CREATED).body(response);
    }

}