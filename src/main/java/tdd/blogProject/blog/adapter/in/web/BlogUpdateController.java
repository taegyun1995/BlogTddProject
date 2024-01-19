package tdd.blogProject.blog.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdd.blogProject.blog.application.port.in.BlogUpdateCommand;
import tdd.blogProject.blog.application.port.in.BlogUpdateUseCase;

@RestController
@RequestMapping("/api/v1")
public class BlogUpdateController {

    private BlogUpdateUseCase blogUpdateUsecase;

    public BlogUpdateController(BlogUpdateUseCase blogUpdateUsecase) {
        this.blogUpdateUsecase = blogUpdateUsecase;
    }

    @PatchMapping("/blog/{blogId}")
    public ResponseEntity<Void> updateBlogName(
            @PathVariable(value = "blogId") Long blogId,
            @RequestBody BlogUpdateCommand command
    ) {
        blogUpdateUsecase.updateBlogTitle(blogId, command);
        return ResponseEntity.ok(null);
    }

}