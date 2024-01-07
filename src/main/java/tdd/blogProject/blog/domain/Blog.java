package tdd.blogProject.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.user.domain.UserName;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class Blog {

    private BlogTitle blogTitle;
    private UserName userName;

    public Blog(BlogTitle blogTitle, UserName userName) {
        this.blogTitle = blogTitle;
        this.userName = userName;
    }

    public static Blog of(BlogCreateCommand command) {
        return new Blog(command.getBlogTitle(), command.getUserName());
    }

}