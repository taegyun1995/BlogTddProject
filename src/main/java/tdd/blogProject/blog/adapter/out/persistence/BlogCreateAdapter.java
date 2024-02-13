package tdd.blogProject.blog.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import tdd.blogProject.blog.application.port.out.BlogCreatePort;
import tdd.blogProject.blog.domain.Blog;

@Repository
public class BlogCreateAdapter implements BlogCreatePort {

    private final JpaBlogRepository jpaBlogRepository;

    public BlogCreateAdapter(JpaBlogRepository jpaBlogRepository) {
        this.jpaBlogRepository = jpaBlogRepository;
    }

    @Override
    public void createBlog(Blog blog) {
        BlogEntity blogEntity = BlogEntity.builder()
                .blogTitle(blog.getBlogTitle())
                .build();

        jpaBlogRepository.save(blogEntity);
    }

}