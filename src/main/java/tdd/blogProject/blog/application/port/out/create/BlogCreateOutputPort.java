package tdd.blogProject.blog.application.port.out.create;

import tdd.blogProject.blog.domain.Blog;

public interface BlogCreateOutputPort {

    void createBlog(Blog blog);

}