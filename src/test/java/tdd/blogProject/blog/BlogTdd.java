package tdd.blogProject.blog;

import org.junit.jupiter.api.*;
import tdd.blogProject.user.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BlogTdd {

    BlogRepository sut;
    User mockUser;
    BlogTitle mockTitle;
    Blog blog;

    @BeforeEach
    void setUp() {
        sut = mock(BlogRepository.class);
        mockUser = mock(User.class);
        mockTitle = mock(BlogTitle.class);
        blog = Blog.of(sut, mockUser, mockTitle);
    }

    @Nested
    @DisplayName("블로그 개설")
    class PublishBlog {
        @Test
        @DisplayName("사용자가 블로그를 개설했어요 :)")
        void testPublishBlog() {
            blog.publish();

            assertEquals(mockUser, blog.getUser());
            assertEquals(mockTitle, blog.getTitle());
            assertEquals(0, blog.getPosts().size());
            verify(sut, times(1)).publishBlog(blog);
        }

        @Test
        @DisplayName("이미 블로그를 개설한 사용자입니다.. 흑..")
        void testAlreadyPublishBlogByUser() {
            blog.publish();

            when(sut.getBlogByUser(mockUser)).thenReturn(Optional.of(blog));

            assertThrows(IllegalStateException.class, blog::publish);
            verify(sut, times(1)).publishBlog(blog);
        }

        @Test
        @DisplayName("블로그를 개설할 수 없다는데요..? RuntimeException")
        void testNotPublishBlogByRuntime() {
            doThrow(new RuntimeException("블로그를 개설할 수 없어요..")).when(sut).publishBlog(blog);

            assertThrows(RuntimeException.class, blog::publish);
            verify(sut, times(1)).publishBlog(blog);
        }

        @Test
        @DisplayName("블로그를 개설할 수 없다는데요..? IllegalStateException")
        void testNotPublishBlogByIllegal() {
            doThrow(new IllegalStateException("블로그를 개설할 수 없어요..")).when(sut).publishBlog(blog);

            assertThrows(IllegalStateException.class, blog::publish);
            verify(sut, times(1)).publishBlog(blog);
        }
    }

    @Nested
    @DisplayName("블로그 검색")
    class SearchBlog {
        @Test
        @DisplayName("특정 사용자의 블로그를 검색할 수 있어요!!!!")
        void testSearchBlogByUser() {
            when(sut.searchBlogByUser(mockUser)).thenReturn(Optional.of(blog));

            Optional<Blog> searchedBlog = sut.searchBlogByUser(mockUser);

            assertTrue(searchedBlog.isPresent());
        }

        @Test
        @DisplayName("특정 사용자의 블로그가 검색이 안됩니다..ㅠㅠ")
        void testNotSearchBlogByUser() {
            when(sut.searchBlogByUser(mockUser)).thenReturn(Optional.empty());

            Optional<Blog> searchedBlog = sut.searchBlogByUser(mockUser);

            assertFalse(searchedBlog.isPresent());
        }

        @Test
        @DisplayName("특정 블로그명을 검색할 수 있어요!!!!")
        void testSearchBlogByTitle() {
            when(sut.searchBlogByTitle(mockTitle)).thenReturn(Optional.of(blog));

            Optional<Blog> searchedBlog = sut.searchBlogByTitle(mockTitle);

            assertTrue(searchedBlog.isPresent());
        }

        @Test
        @DisplayName("특정 블로그명으로 검색이 안됩니다..ㅠㅠ")
        void testNotSearchBlogByTitle() {
            when(sut.searchBlogByTitle(mockTitle)).thenReturn(Optional.empty());

            Optional<Blog> searchedBlog = sut.searchBlogByTitle(mockTitle);

            assertFalse(searchedBlog.isPresent());
        }
    }

    @Nested
    @DisplayName("블로그 삭제")
    class DeleteBlog {
        @Test
        @DisplayName("사용자의 블로그를 삭제합니다..ㅠㅠ")
        void testDeleteBlog() {
            when(sut.existBlogByUserAndTitle(mockUser, mockTitle)).thenReturn(true);

            assertDoesNotThrow(blog::deleteBlog);
            verify(sut, times(1)).deleteBlog(blog);
            assertEquals(0, blog.getPosts().size());
        }

        @Test
        @DisplayName("사용자 블로그를 삭제할 수 없습니다..ㅠㅠ Runtime..")
        void testNotDeleteBlogByRuntime() {
            when(sut.existBlogByUserAndTitle(mockUser, mockTitle)).thenReturn(true);
            doThrow(new RuntimeException("블로그를 삭제가 되지 않습니다.")).when(sut).deleteBlog(blog);

            assertThrows(RuntimeException.class, blog::deleteBlog);
            verify(sut, times(1)).deleteBlog(blog);
        }

        @Test
        @DisplayName("사용자 블로그를 삭제할 수 없습니다..ㅠㅠ IllegalState..")
        void testNotDeleteBlogByIllegal() {
            when(sut.existBlogByUserAndTitle(mockUser, mockTitle)).thenReturn(false);
            doThrow(new IllegalStateException("블로그를 삭제가 되지 않습니다.")).when(sut).deleteBlog(blog);

            assertThrows(IllegalStateException.class, blog::deleteBlog);
            verify(sut, times(0)).deleteBlog(blog);
        }
    }

}