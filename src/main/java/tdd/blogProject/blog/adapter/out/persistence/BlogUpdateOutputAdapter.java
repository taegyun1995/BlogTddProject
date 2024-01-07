package tdd.blogProject.blog.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import tdd.blogProject.blog.application.port.out.BlogUpdateOutputPort;
import tdd.blogProject.blog.domain.BlogTitle;

@Repository
public class BlogUpdateOutputAdapter implements BlogUpdateOutputPort {

    private final JpaBlogRepository repository;

    public BlogUpdateOutputAdapter(JpaBlogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateBlogTitle(Long blogId, BlogTitle blogTitle) {
        BlogEntity blog = repository.findById(blogId).orElseThrow(
                () -> new IllegalArgumentException("블로그를 찾을 수 없어!!")
        );
    }

}