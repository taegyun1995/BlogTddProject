package tdd.blogProject.blog.adapter.out.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.blog.domain.Blog;
import tdd.blogProject.blog.domain.BlogTitle;
import tdd.blogProject.user.adapter.out.persistence.UserEntity;
import tdd.blogProject.user.domain.UserName;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BlogCreateAdapterTest {

    JpaBlogRepository repository;
    BlogCreateAdapter sut;

    @BeforeEach
    void setUp() {
        repository = mock(JpaBlogRepository.class);
        sut = new BlogCreateAdapter(repository);
    }

    @Test
    @DisplayName("Create Blog Adapter Test - Positive Case")
    void testBlogCreateAdapterPositiveCase() {
        BlogCreateCommand command = new BlogCreateCommand(new BlogTitle("Test Blog Title"), new UserName("Test User"));
        BlogEntity blog = BlogEntity.of(command, createDummyUser());

        when(repository.save(blog)).thenReturn(blog);
        sut.createBlog(Blog.of(command));

        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Create Blog Adapter Test - Negative Case (Blog is null)")
    void testBlogCreateAdapterNegativeCaseNullCommand() {
        assertThrows(NullPointerException.class, () -> sut.createBlog(null));
        verify(repository, times(0)).save(any());
    }

    private UserEntity createDummyUser() {
        UserName dummyUserName = new UserName("dummyUser");
        return UserEntity.builder()
                .userName(dummyUserName)
                .build();
    }

}