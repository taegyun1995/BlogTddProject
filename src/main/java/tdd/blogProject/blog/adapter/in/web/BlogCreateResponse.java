package tdd.blogProject.blog.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.blog.domain.Blog;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class BlogCreateResponse {

    private String title;

    public BlogCreateResponse(String title) {
        this.title = title;
    }

    public static BlogCreateResponse of(Blog blog) {
        return new BlogCreateResponse(blog.getBlogTitle().getValue());
    }

}