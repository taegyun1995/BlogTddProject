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
    private BlogTitle title;

    @Builder
    public Blog(BlogRepository blogRepository, User user, List<Post> posts, BlogTitle title) {
        this.blogRepository = blogRepository;
        this.user = user;
        this.posts = posts;
        this.title = title;
    }

    public static Blog of(BlogRepository blogRepository, User user, BlogTitle title) {
        return Blog.builder()
                .blogRepository(blogRepository)
                .user(user)
                .posts(new ArrayList<>())
                .title(title)
                .build();
    }

    public void publish() {
        validationBlogPublish(blogRepository, user, title);

        blogRepository.publishBlog(this);
    }

    public void deleteBlog() {
        if (!blogRepository.existBlogByUserAndTitle(user, title)) {
            throw new IllegalStateException("해당 사용자의 블로그가 존재하지 않습니다.");
        }

        blogRepository.deleteBlog(this);
    }

    private void validationBlogPublish(BlogRepository blogRepository, User user, BlogTitle title) {
        if (blogRepository.existBlogByTitle(title)) {
            throw new IllegalStateException("이미 사용중인 블로그명입니다.");
        }

        blogRepository.getBlogByUser(user).ifPresent(blog -> {
            throw new IllegalStateException("이미 사용자가 블로그를 개설했습니다.");
        });
    }

}