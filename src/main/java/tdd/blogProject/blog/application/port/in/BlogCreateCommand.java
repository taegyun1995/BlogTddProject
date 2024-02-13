package tdd.blogProject.blog.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.blog.domain.BlogTitle;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class BlogCreateCommand {

    private BlogTitle blogTitle;

    public BlogCreateCommand(BlogTitle blogTitle) {
        this.blogTitle = blogTitle;
    }

}