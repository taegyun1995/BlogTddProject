package tdd.blogProject.blog.application.port.in.update;

public interface BlogUpdateInputPort {

    void updateBlogTitle(Long blogId, BlogUpdateCommand command);

}