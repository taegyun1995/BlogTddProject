package tdd.blogProject.blog.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.blog.domain.BlogTitle;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogUpdateCommand {

    private BlogTitle blogTitle;

    public BlogUpdateCommand(BlogTitle blogTitle) {
        this.blogTitle = blogTitle;
    }

}