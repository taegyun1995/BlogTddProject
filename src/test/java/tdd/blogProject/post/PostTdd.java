package tdd.blogProject.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tdd.blogProject.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PostTdd {

    PostRepository sut;
    User mockUser;
    PostTitle mockTitle;
    PostContent mockContent;

    @BeforeEach
    void setUp() {
        sut = mock(PostRepository.class);
        mockUser = mock(User.class);
        mockTitle = mock(PostTitle.class);
        mockContent = mock(PostContent.class);
    }

    @Nested
    @DisplayName("게시글 생성")
    class PostCreate {

        private Post post;

        @BeforeEach
        void setUp() {
            post = Post.of(sut, mockUser, mockTitle, mockContent);
        }

        @Test
        @DisplayName("사용자가 게시글을 생성했습니다 :)")
        void testPublishPost() {
            post.publish();

            assertEquals(mockUser, post.getUser());
            assertEquals(mockTitle, post.getTitle());
            assertEquals(0, post.getComments().size());
            verify(sut, times(1)).createPost(post);
        }

        @Test
        @DisplayName("게시글을 생성할 수 없습니다.")
        void testNotPublishPostByRuntime() {
            doThrow(new RuntimeException("게시글을 개시할 수 없습니다.")).when(sut).createPost(post);

            assertThrows(RuntimeException.class, post::publish);
            verify(sut, times(1)).createPost(post);
        }

        @Test
        @DisplayName("게시글을 생성할 수 없습니다.")
        void testNotPublishPostByIllegal() {
            doThrow(new IllegalStateException("게시글을 개시할 수 없습니다.")).when(sut).createPost(post);

            assertThrows(IllegalStateException.class, post::publish);
            verify(sut, times(1)).createPost(post);
        }
    }

}