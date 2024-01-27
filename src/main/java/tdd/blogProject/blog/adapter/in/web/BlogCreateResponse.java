package tdd.blogProject.blog.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class BlogCreateResponse {

    private String title;
    private String userName;

    public BlogCreateResponse(String title, String userName) {
        this.title = title;
        this.userName = userName;
    }

}