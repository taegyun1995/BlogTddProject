package tdd.blogProject.blog.application.port.in;

public interface BlogUpdateUseCase {

    void updateBlogTitle(Long blogId, BlogUpdateCommand command);

}