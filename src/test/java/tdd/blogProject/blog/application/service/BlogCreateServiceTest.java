package tdd.blogProject.blog.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.blogProject.advice.exception.custom.BadRequestException;
import tdd.blogProject.advice.exception.custom.LengthRequiredException;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.blog.application.port.out.BlogCreatePort;
import tdd.blogProject.blog.domain.Blog;
import tdd.blogProject.blog.domain.BlogTitle;
import tdd.blogProject.user.domain.UserName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static tdd.blogProject.advice.exception.ExceptionCodeConst.BAD_REQUEST_TITLE;
import static tdd.blogProject.advice.exception.ExceptionCodeConst.LENGTH_REQUIRED_TITLE;

@ExtendWith(MockitoExtension.class)
public class BlogCreateServiceTest {

    @Captor
    private ArgumentCaptor<Blog> blog;

    @Mock
    private BlogCreatePort port;

    @InjectMocks
    private BlogCreateService sut;

    @Test
    @DisplayName("Create Blog Service Test - Positive Case")
    void testCreateBlogServicePositiveCase() {
        BlogCreateCommand command = new BlogCreateCommand(new BlogTitle("Test Blog Title"), new UserName("Test User"));

        sut.createBlog(command);

        verify(port, times(1)).createBlog(blog.capture());

        Blog capturedBlog = blog.getValue();
        assertEquals(command.getBlogTitle(), capturedBlog.getBlogTitle());
        assertEquals(command.getUserName(), capturedBlog.getUserName());
    }

    @Test
    @DisplayName("Create Blog Service Test - Negative Case (블로그 제목이 NPE)")
    void testCreateBlogServiceNegativeCaseNPETitle() {
        BlogCreateCommand command = mock(BlogCreateCommand.class);

        when(command.getBlogTitle()).thenThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () -> sut.createBlog(command));
        verify(command, times(1)).getBlogTitle();
        verify(port, times(0)).createBlog(any());
    }

    @Test
    @DisplayName("Create Blog Service Test - Negative Case (블로그 제목이 없음)")
    void testCreateBlogServiceNegativeCaseBadRequestTitle() {
        BlogCreateCommand command = mock(BlogCreateCommand.class);

        when(command.getBlogTitle()).thenThrow(new BadRequestException(BAD_REQUEST_TITLE));

        assertThrows(BadRequestException.class, () -> sut.createBlog(command));
        verify(command, times(1)).getBlogTitle();
        verify(port, times(0)).createBlog(any());
    }

    @Test
    @DisplayName("Create Blog Service Test - Negative Case (블로그 제목 20글자 초과)")
    void testCreateBlogServiceNegativeCaseTitleLengthExceeds20() {
        BlogCreateCommand command = mock(BlogCreateCommand.class);

        when(command.getBlogTitle()).thenThrow(new LengthRequiredException(LENGTH_REQUIRED_TITLE));

        assertThrows(LengthRequiredException.class, () -> sut.createBlog(command));
        verify(command, times(1)).getBlogTitle();
        verify(port, times(0)).createBlog(any());
    }

}