package tdd.blogProject.blog.application.port.out;

import tdd.blogProject.blog.domain.BlogTitle;

public interface BlogUpdatePort {

    void updateBlogTitle(Long blogId, BlogTitle blogTitle);

}