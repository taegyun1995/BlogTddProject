package tdd.blogProject.blog.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class BlogCreateResponse {

    private String title;

    public BlogCreateResponse(String title) {
        this.title = title;
    }

    public static BlogCreateResponse of(BlogCreateCommand command) {
        return new BlogCreateResponse(command.getBlogTitle().getValue());
    }

}