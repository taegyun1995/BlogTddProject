package tdd.blogProject.post;

import tdd.blogProject.user.User;

public interface PostRepository {

    void publishPost(Post post);

    void modifyPost(User user, String title, String content);

}