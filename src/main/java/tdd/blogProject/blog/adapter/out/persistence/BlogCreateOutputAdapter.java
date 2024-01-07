package tdd.blogProject.blog.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import tdd.blogProject.blog.application.port.out.BlogCreateOutputPort;
import tdd.blogProject.blog.domain.Blog;
import tdd.blogProject.user.domain.UserEntity;
import tdd.blogProject.user.domain.UserName;

@Repository
public class BlogCreateOutputAdapter implements BlogCreateOutputPort {

    private final JpaBlogRepository repository;

    public BlogCreateOutputAdapter(JpaBlogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createBlog(Blog blog) {
        UserEntity userEntity = createDummyUser();

        BlogEntity blogEntity = BlogEntity.builder()
                .blogTitle(blog.getBlogTitle())
                .userEntity(userEntity)
                .build();

        repository.save(blogEntity);
    }

    private UserEntity createDummyUser() {
        UserName dummyUserName = new UserName("dummyUser");
        return UserEntity.builder()
                .userName(dummyUserName)
                .build();
    }

}