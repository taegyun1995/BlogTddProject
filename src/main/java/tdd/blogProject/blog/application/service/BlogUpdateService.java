package tdd.blogProject.blog.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tdd.blogProject.blog.adapter.in.web.BlogUpdateResponse;
import tdd.blogProject.blog.application.port.in.BlogUpdateCommand;
import tdd.blogProject.blog.application.port.in.BlogUpdateUseCase;
import tdd.blogProject.blog.application.port.out.BlogUpdatePort;
import tdd.blogProject.blog.domain.BlogTitle;

@Service
public class BlogUpdateService implements BlogUpdateUseCase {

    private BlogUpdatePort blogUpdatePort;

    public BlogUpdateService(BlogUpdatePort blogUpdatePort) {
        this.blogUpdatePort = blogUpdatePort;
    }

    @Override
    @Transactional
    public BlogUpdateResponse updateBlogTitle(Long blogId, BlogUpdateCommand command) {
        blogUpdatePort.updateBlogTitle(blogId, BlogTitle.of(command));
        return BlogUpdateResponse.of(command);
    }

}