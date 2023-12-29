package tdd.blogProject.blog;

import tdd.blogProject.user.User;

import java.util.Optional;

public interface BlogRepository {

    void createBlog(Blog blog);

    Optional<Blog> getBlogByUser(User user);

    Optional<Blog> searchBlogByUser(User user);

    Optional<Blog> searchBlogByTitle(BlogTitle title);

    boolean existBlogByTitle(BlogTitle title);

    boolean existBlogByUserAndTitle(User user, BlogTitle title);

    void deleteBlog(Blog blog);

}