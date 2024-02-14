package tdd.blogProject.blog.application.port.in;

import tdd.blogProject.blog.adapter.in.web.BlogUpdateResponse;

public interface BlogUpdateUseCase {

    BlogUpdateResponse updateBlogTitle(Long blogId, BlogUpdateCommand command);

}