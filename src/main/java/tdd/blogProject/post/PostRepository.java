package tdd.blogProject.post;

public interface PostRepository {

    void publishPost(Post post);

    void modifyPost(User user, String title, String content);

}