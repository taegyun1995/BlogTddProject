package tdd.blogProject.blog.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tdd.blogProject.blog.adapter.in.web.BlogCreateResponse;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.blog.application.port.in.BlogCreateUseCase;
import tdd.blogProject.blog.application.port.out.BlogCreatePort;
import tdd.blogProject.blog.domain.Blog;

@Service
public class BlogCreateService implements BlogCreateUseCase {

    private final BlogCreatePort blogCreatePort;

    public BlogCreateService(BlogCreatePort blogCreatePort) {
        this.blogCreatePort = blogCreatePort;
    }

    @Override
    @Transactional
    public BlogCreateResponse createBlog(BlogCreateCommand command) {
        Blog blog = Blog.of(command);
        blogCreatePort.createBlog(blog);

        return blogCreatePort.createResponse(blog);
    }

}