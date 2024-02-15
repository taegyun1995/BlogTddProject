package tdd.blogProject.blog.application.port.out;

import tdd.blogProject.blog.adapter.in.web.BlogCreateResponse;
import tdd.blogProject.blog.domain.Blog;

public interface BlogCreatePort {

    void createBlog(Blog blog);

    BlogCreateResponse createResponse(Blog blog);

}