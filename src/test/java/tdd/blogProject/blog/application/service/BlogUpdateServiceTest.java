package tdd.blogProject.blog.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tdd.blogProject.blog.application.port.in.BlogUpdateCommand;
import tdd.blogProject.blog.application.port.out.BlogUpdatePort;
import tdd.blogProject.blog.domain.BlogTitle;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BlogUpdateServiceTest {

    BlogUpdatePort port;
    BlogUpdateService sut;

    @BeforeEach
    void setUp() {
        port = mock(BlogUpdatePort.class);
        sut = new BlogUpdateService(port);
    }

    @Test
    @DisplayName("Update Blog Service Test - Positive Case")
    void positiveCaseBlogUpdateService() {
        long blogId = 1L;
        BlogUpdateCommand command = new BlogUpdateCommand(new BlogTitle("update blog title"));

        sut.updateBlogTitle(blogId, command);

        verify(port, times(1)).updateBlogTitle(any(), any());
    }

    @Test
    @DisplayName("Update Blog Service Test - Negative Cse (Command is null")
    void negativeCaseBlogUpdateServiceNullCommand() {
        assertThrows(NullPointerException.class, () -> sut.updateBlogTitle(null, null));
        verify(port, times(0)).updateBlogTitle(any(), any());
    }

}