package tdd.blogProject.blog.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdd.blogProject.blog.application.port.in.BlogUpdateCommand;
import tdd.blogProject.blog.application.port.in.BlogUpdateInputPort;

@RestController
@RequestMapping("/api/v1")
public class BlogUpdateController {

    private BlogUpdateInputPort blogUpdateInputPort;

    public BlogUpdateController(BlogUpdateInputPort blogUpdateInputPort) {
        this.blogUpdateInputPort = blogUpdateInputPort;
    }

    @PatchMapping("/blog/{blogId}")
    public ResponseEntity<Void> updateBlogName(
            @PathVariable("blogId") Long blogId,
            @RequestBody BlogUpdateCommand command
    ) {
        blogUpdateInputPort.updateBlogTitle(blogId, command);
        return ResponseEntity.ok(null);
    }

}