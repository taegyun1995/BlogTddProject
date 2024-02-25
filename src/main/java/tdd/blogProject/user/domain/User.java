package tdd.blogProject.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.user.application.port.in.SignUpCommand;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class User {

    private LoginId loginId;
    private Password password;
    private UserName userName;
    private NickName nickname;

    public User(LoginId loginId, Password password, UserName userName, NickName nickname) {
        this.loginId = loginId;
        this.password = password;
        this.userName = userName;
        this.nickname = nickname;
    }

    public static User of(SignUpCommand command) {
        return new User(
                command.getLoginId(),
                command.getPassword(),
                command.getUserName(),
                command.getNickname()
        );
    }

}