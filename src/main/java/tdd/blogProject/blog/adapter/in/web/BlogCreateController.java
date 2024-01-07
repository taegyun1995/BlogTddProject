package tdd.blogProject.blog.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.blog.application.port.in.BlogCreateInputPort;

@RestController
@RequestMapping("/api/v1")
public class BlogCreateController {

    private BlogCreateInputPort blogCreateInputPort;

    public BlogCreateController(BlogCreateInputPort blogCreateInputPort) {
        this.blogCreateInputPort = blogCreateInputPort;
    }

    @PostMapping("/blog")
    public ResponseEntity<Void> createBlog(
            @RequestBody BlogCreateCommand command
    ) {
        blogCreateInputPort.createBlog(command);
        return ResponseEntity.ok(null);
    }

}