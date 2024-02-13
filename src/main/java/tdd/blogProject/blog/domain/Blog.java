package tdd.blogProject.blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;

import java.util.Objects;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class Blog {

    private BlogTitle blogTitle;

    public Blog(BlogTitle blogTitle) {
        this.blogTitle = blogTitle;
    }

    public static Blog of(BlogCreateCommand command) {
        return new Blog(new BlogTitle(command.getBlogTitle().getValue()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return Objects.equals(blogTitle, blog.blogTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogTitle);
    }

}