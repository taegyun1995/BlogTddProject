package tdd.blogProject.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tdd.blogProject.user.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BlogTdd {

    User mockUser;
    BlogRepository sut;

    @BeforeEach
    void setUp() {
        mockUser = mock(User.class);
        sut = mock(BlogRepository.class);
    }

    @Test
    @DisplayName("사용자가 최초로 블로그를 개설했다.")
    void testCreateBlogByUser() {
        Blog blog = Blog.of(sut, mockUser);

        assertEquals(0, blog.getPosts().size());
        verify(sut).createBlog(blog);
    }

    @Test
    @DisplayName("이미 블로그를 개설한 사용자입니다.")
    void testAlreadyCreateBlogByUser() {
        when(sut.getBlogByUser(mockUser)).thenReturn(Optional.of(new Blog()));

        assertThrows(IllegalStateException.class, () -> Blog.of(sut, mockUser));
    }

    @Test
    @DisplayName("특정 사용자의 블로그를 검색합니다.")
    void testSearchBlogByUser() {
        when(sut.searchBlogByUser(mockUser)).thenReturn(Optional.of(new Blog()));

        Optional<Blog> searchedBlog = sut.searchBlogByUser(mockUser);

        assertTrue(searchedBlog.isPresent());
    }

    @Test
    @DisplayName("특정 사용자의 블로그가 검색이 안됩니다.")
    void testNotSearchBlogByUser() {
        when(sut.searchBlogByUser(mockUser)).thenReturn(Optional.empty());

        Optional<Blog> searchedBlog = sut.searchBlogByUser(mockUser);

        assertFalse(searchedBlog.isPresent());
    }

}