package tdd.blogProject.blog.application.port.in;

public interface BlogUpdateInputPort {

    void updateBlogTitle(Long blogId, BlogUpdateCommand command);

}