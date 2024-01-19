package tdd.blogProject.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.user.adapter.out.persistence.UserEntity;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class User {

    private UserName userName;

    public User(UserName userName) {
        this.userName = userName;
    }

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .userName(user.getUserName())
                .build();
    }

}