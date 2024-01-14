package tdd.blogProject.blog.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.blog.application.port.in.BlogCreateCommand;
import tdd.blogProject.blog.domain.BlogTitle;
import tdd.blogProject.user.adapter.out.persistence.UserEntity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Table(name = "blog")
@Entity
@NoArgsConstructor(access = PROTECTED)
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "blog_id")
    private Long id;

    @Embedded
    private BlogTitle blogTitle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Builder
    public BlogEntity(Long id, BlogTitle blogTitle, UserEntity userEntity) {
        this.id = id;
        this.blogTitle = blogTitle;
        this.userEntity = userEntity;
    }

    public static BlogEntity of(BlogCreateCommand request, UserEntity userEntity) {
        return BlogEntity.builder()
                .blogTitle(request.getBlogTitle())
                .userEntity(userEntity)
                .build();
    }

}