package tdd.blogProject.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.comment.Comment;
import tdd.blogProject.user.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    private PostRepository postRepository;
    private User user;
    private PostTitle title;
    private PostContent content;
    private List<Comment> comments;

    @Builder
    public Post(PostRepository postRepository, User user, PostTitle title, PostContent content, List<Comment> comments) {
        this.postRepository = postRepository;
        this.user = user;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public static Post of(PostRepository postRepository, User user, PostTitle title, PostContent content) {
        return Post.builder()
                .postRepository(postRepository)
                .user(user)
                .title(title)
                .content(content)
                .comments(new ArrayList<>())
                .build();
    }

    public void publish() {
        validationPostPublish(user, title, content);

        this.postRepository.createPost(this);
    }

    public static void validationPostPublish(User user, PostTitle title, PostContent content) {
        if (user == null || title == null || content == null) {
            throw new IllegalStateException("게시할 수 없는 게시글입니다.");
        }

        // 추가 필요..
    }

}