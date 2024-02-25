package tdd.blogProject.user.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.user.domain.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Table(name = "users")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Embedded
    private LoginId loginId;

    @Embedded
    private Password password;

    @Embedded
    private UserName userName;

    @Embedded
    private NickName nickName;

    @Builder
    public UserEntity(LoginId loginId, Password password, UserName userName, NickName nickName) {
        this.loginId = loginId;
        this.password = password;
        this.userName = userName;
        this.nickName = nickName;
    }

    public static UserEntity of(User user) {
        return UserEntity.builder()
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .userName(user.getUserName())
                .nickName(user.getNickname())
                .build();
    }

}