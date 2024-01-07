package tdd.blogProject.blog.adapter.in.web;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tdd.blogProject.blog.application.port.in.BlogUpdateCommand;
import tdd.blogProject.blog.application.port.in.BlogUpdateInputPort;
import tdd.blogProject.blog.application.port.out.BlogUpdateOutputPort;
import tdd.blogProject.blog.domain.BlogTitle;

@Service
public class BlogUpdateInputAdapter implements BlogUpdateInputPort {

    private BlogUpdateOutputPort blogUpdateOutputPort;

    public BlogUpdateInputAdapter(BlogUpdateOutputPort blogUpdateOutputPort) {
        this.blogUpdateOutputPort = blogUpdateOutputPort;
    }

    @Override
    @Transactional
    public void updateBlogTitle(Long blogId, BlogUpdateCommand command) {
        blogUpdateOutputPort.updateBlogTitle(blogId, BlogTitle.of(command));
    }

}