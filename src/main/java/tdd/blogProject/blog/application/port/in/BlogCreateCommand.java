package tdd.blogProject.blog.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.blog.domain.BlogTitle;
import tdd.blogProject.user.domain.UserName;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogCreateCommand that = (BlogCreateCommand) o;
        return Objects.equals(blogTitle, that.blogTitle) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogTitle, userName);
    }

}