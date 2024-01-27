package tdd.blogProject.blog.application.port.in;

import tdd.blogProject.blog.adapter.in.web.BlogCreateResponse;

public interface BlogCreateUseCase {

    BlogCreateResponse createBlog(BlogCreateCommand command);

}