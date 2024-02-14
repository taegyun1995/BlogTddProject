package tdd.blogProject.blog.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.blog.application.port.in.BlogUpdateCommand;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class BlogUpdateResponse {

    private String title;

    public BlogUpdateResponse(String title) {
        this.title = title;
    }

    public static BlogUpdateResponse of(BlogUpdateCommand command) {
        return new BlogUpdateResponse(command.getBlogTitle().getValue());
    }

}