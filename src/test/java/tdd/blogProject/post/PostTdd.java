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
    Post post;

    @BeforeEach
    void setUp() {
        sut = mock(PostRepository.class);
        mockUser = mock(User.class);
        mockTitle = mock(PostTitle.class);
        mockContent = mock(PostContent.class);
        post = Post.of(mockUser, mockTitle, mockContent);
    }

    @Nested
    @DisplayName("게시글 생성")
    class PostCreate {
        @Test
        @DisplayName("사용자가 게시글을 생성했습니다 :)")
        void testPublishPost() {
            post.publish();

            assertEquals(mockUser, post.getUser());
            assertEquals(mockTitle, post.getTitle());
            assertEquals(0, post.getComments().size());
            verify(sut, times(1)).publishPost(post);
        }

        @Test
        @DisplayName("게시글을 생성할 수 없습니다.. RuntimeException")
        void testNotPublishPostByRuntime() {
            doThrow(new RuntimeException("게시글을 개시할 수 없습니다.")).when(sut).publishPost(post);

            assertThrows(RuntimeException.class, post::publish);
            verify(sut, times(1)).publishPost(post);
        }

        @Test
        @DisplayName("게시글을 생성할 수 없습니다.. IllegalStateException")
        void testNotPublishPostByIllegal() {
            doThrow(new IllegalStateException("게시글을 개시할 수 없습니다.")).when(sut).publishPost(post);

            assertThrows(IllegalStateException.class, post::publish);
            verify(sut, times(1)).publishPost(post);
        }
    }

    @Nested
    @DisplayName("게시글 수정")
    class PostModify {
        @Test
        @DisplayName("게시글을 수정할 수 있습니다.")
        void testModifyPost() {
            post.modify(mockUser, "Title", "Content");

            verify(sut, times(1)).modifyPost(any(), any(), any());
        }

        @Test
        @DisplayName("본인 게시글이 아닌데 어케 수정하시려구요!!")
        void testNotModifyPostByDifferentUser() {

        }

        @Test
        @DisplayName("게시글을 수정할 때 제목이나 내용이 null이면 예외 발생해요!!!!")
        void testNotModifyPostWithNullTitleOrContent() {
            assertThrows(IllegalArgumentException.class,
                    () -> post.modify(mockUser, null, "content"));
            verify(sut, times(0)).modifyPost(mockUser, null, "content");

            assertThrows(IllegalArgumentException.class,
                    () -> post.modify(mockUser, "title", null));
            verify(sut, times(0)).modifyPost(mockUser, "title", null);
        }

        @Test
        @DisplayName("게시글을 수정할 때 제목이 10자 이상이면 예외 발생")
        void testNotModifyPostWithLongTitle() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> post.modify(mockUser, "too long modify title", "content"));
            assertEquals("제목은 10자를 초과할 수 없습니다", exception.getMessage());
            verify(sut, times(0)).modifyPost(mockUser, "too long modify title", "modify content");
        }

        @Test
        @DisplayName("게시글을 수정할 때 내용이 1000자 이상이면 예외 발생")
        void testNotModifyPostWithLongContent() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> post.modify(mockUser, "title", createLongContent().getValue()));

            assertEquals("내용은 1000자를 초과할 수 없습니다", exception.getMessage());
            verify(sut, times(0)).modifyPost(mockUser, "title", createLongContent().getValue());
        }

        private PostContent createLongContent() {
            StringBuilder longContent = new StringBuilder();
            for (int i = 0; i < 1001; i++) {
                longContent.append("z");
            }
            return new PostContent(longContent.toString());
        }
    }

    @Nested
    @DisplayName("게시글 삭제")
    class PostDelete {
        @Test
        @DisplayName("본인 게시글을 삭제할 수 있습니다.")
        void testDeletePostByUser() {

        }

        @Test
        @DisplayName("본인 게시글이 아니면 삭제할 수 없습니다요..?")
        void testNotDeletePostByDifferentUser() {

        }

        @Test
        @DisplayName("게시글을 삭제할 수 없습니다.. RuntimeException")
        void testNotDeletePostByRuntime() {

        }

        @Test
        @DisplayName("게시글을 삭제할 수 없습니다.. IllegalStateException")
        void testNotDeletePostByIllegal() {

        }
    }

    @Nested
    @DisplayName("게시글 검색")
    class PostSearch {
        @Test
        @DisplayName("게시글을 제목으로 검색해서 찾아봅시다!!!")
        void testSearchPostByTitle() {

        }

        @Test
        @DisplayName("게시글을 제목으로 검색해서 안나오지요~")
        void testNotSearchPostByTitle() {

        }
    }

}