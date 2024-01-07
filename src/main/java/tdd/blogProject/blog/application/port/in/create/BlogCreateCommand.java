package tdd.blogProject.blog.application.port.in.create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.blog.domain.BlogTitle;
import tdd.blogProject.user.domain.UserName;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class BlogCreateCommand {

    private BlogTitle blogTitle;
    private UserName userName;

    public BlogCreateCommand(BlogTitle blogTitle, UserName userName) {
        this.blogTitle = blogTitle;
        this.userName = userName;
    }

}