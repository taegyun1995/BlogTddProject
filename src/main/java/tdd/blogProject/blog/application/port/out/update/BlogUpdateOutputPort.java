package tdd.blogProject.blog.application.port.out.update;

import tdd.blogProject.blog.domain.BlogTitle;

public interface BlogUpdateOutputPort {

    void updateBlogTitle(Long blogId, BlogTitle blogTitle);

}