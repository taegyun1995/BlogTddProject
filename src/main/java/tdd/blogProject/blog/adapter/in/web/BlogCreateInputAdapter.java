package tdd.blogProject.blog.adapter.in.web;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.blog.application.port.in.BlogCreateInputPort;
import tdd.blogProject.blog.application.port.out.BlogCreateOutputPort;
import tdd.blogProject.blog.domain.Blog;

@Service
public class BlogCreateInputAdapter implements BlogCreateInputPort {

    private final BlogCreateOutputPort blogCreateOutputPort;

    public BlogCreateInputAdapter(BlogCreateOutputPort blogCreateOutputPort) {
        this.blogCreateOutputPort = blogCreateOutputPort;
    }

    @Override
    @Transactional
    public void createBlog(BlogCreateCommand command) {
        blogCreateOutputPort.createBlog(Blog.of(command));
    }

}