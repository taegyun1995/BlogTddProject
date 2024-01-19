package tdd.blogProject.user.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.user.domain.User;
import tdd.blogProject.user.domain.UserName;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Table(name = "users")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Embedded
    private UserName userName;

    @Builder
    public UserEntity(UserName userName) {
        this.userName = userName;
    }

    public static UserEntity of(User user) {
        return UserEntity.builder()
                .userName(user.getUserName())
                .build();
    }

}