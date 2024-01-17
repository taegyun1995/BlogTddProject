package tdd.blogProject.blog.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.blog.application.port.out.BlogCreatePort;
import tdd.blogProject.blog.domain.BlogTitle;
import tdd.blogProject.user.domain.UserName;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BlogCreateServiceTest {

    BlogCreatePort port;
    BlogCreateService sut;

    @BeforeEach
    void setUp() {
        port = mock(BlogCreatePort.class);
        sut = new BlogCreateService(port);
    }

    @Test
    @DisplayName("Create Blog Service Test - Positive Case")
    void testCreateBlogServicePositiveCase() {
        BlogCreateCommand command = new BlogCreateCommand(new BlogTitle("Test Blog Title"), new UserName("Test User"));

        sut.createBlog(command);

        verify(port, times(1)).createBlog(any());
    }

    @Test
    @DisplayName("Create Blog Service Test - Negative Case (Command is null)")
    void testCreateBlogServiceNegativeCaseNullCommand() {
        assertThrows(NullPointerException.class, () -> sut.createBlog(null));
        verify(port, times(0)).createBlog(any());
    }

}