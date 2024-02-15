package tdd.blogProject.blog.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.advice.entityBase.TimeEntity;
import tdd.blogProject.blog.domain.Blog;
import tdd.blogProject.blog.domain.BlogTitle;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Table(name = "blog")
@Entity
@NoArgsConstructor(access = PROTECTED)
public class BlogEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "blog_id")
    private Long id;

    @Embedded
    private BlogTitle blogTitle;

    @Builder
    public BlogEntity(Long id, BlogTitle blogTitle) {
        this.id = id;
        this.blogTitle = blogTitle;
    }

    public static BlogEntity of(Blog blog) {
        return BlogEntity.builder()
                .blogTitle(blog.getBlogTitle())
                .build();
    }

}