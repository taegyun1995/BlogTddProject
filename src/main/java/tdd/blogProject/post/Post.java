package tdd.blogProject.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.comment.Comment;
import tdd.blogProject.user.domain.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    private User user;
    private PostTitle title;
    private PostContent content;
    private List<Comment> comments;

    @Builder
    public Post(User user, PostTitle title, PostContent content, List<Comment> comments) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public static Post of(User user, PostTitle title, PostContent content) {
        return Post.builder()
                .user(user)
                .title(title)
                .content(content)
                .comments(new ArrayList<>())
                .build();
    }

    public void publish() {
        validationPostPublish(user, title, content);
    }

    public void validationPostPublish(User user, PostTitle title, PostContent content) {
        if (user == null || title == null || content == null) {
            throw new IllegalStateException("게시할 수 없는 게시글입니다.");
        }
    }

    public void modify(User user, String title, String content) {
        if (title == null || content == null) {
            throw new IllegalArgumentException("제목 또는 내용은 null일 수 없습니다.");
        }
        if (title.length() > 10) {
            throw new IllegalArgumentException("제목은 10자를 초과할 수 없습니다");
        }
        if (content.length() > 1000) {
            throw new IllegalArgumentException("내용은 1000자를 초과할 수 없습니다");
        }

        this.title = new PostTitle(title);
        this.content = new PostContent(content);
    }

}