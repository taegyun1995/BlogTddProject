package tdd.blogProject.user.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.user.domain.LoginId;
import tdd.blogProject.user.domain.NickName;
import tdd.blogProject.user.domain.Password;
import tdd.blogProject.user.domain.UserName;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class SignUpCommand {

    private LoginId loginId;
    private Password password;
    private UserName userName;
    private NickName nickname;

    public SignUpCommand(LoginId loginId, Password password, UserName userName, NickName nickname) {
        this.loginId = loginId;
        this.password = password;
        this.userName = userName;
        this.nickname = nickname;
    }

}