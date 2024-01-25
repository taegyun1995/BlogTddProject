package tdd.blogProject.user.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.user.domain.LoginId;
import tdd.blogProject.user.domain.Password;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class UserSignUpCommand {

    private LoginId loginId;
    private Password password;

    public UserSignUpCommand(LoginId loginId, Password password) {
        this.loginId = loginId;
        this.password = password;
    }

}