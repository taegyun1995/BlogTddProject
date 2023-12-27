package tdd.blogProject.blog;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.post.Post;
import tdd.blogProject.user.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blog {

    private BlogRepository blogRepository;
    private User user;
    private List<Post> posts;

    @Builder
    public Blog(BlogRepository blogRepository, User user, List<Post> posts) {
        this.blogRepository = blogRepository;
        this.user = user;
        this.posts = posts;
    }

    public static Blog of(BlogRepository blogRepository, User user) {
        blogRepository.getBlogByUser(user).ifPresent(blog -> {
            throw new IllegalStateException("이미 사용자가 블로그를 개설했습니다.");
        });

        Blog blog = Blog.builder()
                .blogRepository(blogRepository)
                .user(user)
                .posts(new ArrayList<>())
                .build();

        blogRepository.createBlog(blog);

        return blog;
    }

}