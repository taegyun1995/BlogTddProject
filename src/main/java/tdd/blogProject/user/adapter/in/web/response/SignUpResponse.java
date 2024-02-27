package tdd.blogProject.user.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.user.domain.LoginId;
import tdd.blogProject.user.domain.NickName;
import tdd.blogProject.user.domain.User;
import tdd.blogProject.user.domain.UserName;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class SignUpResponse {

    private LoginId loginId;
    private UserName userName;
    private NickName nickName;

    @Builder
    public SignUpResponse(LoginId loginId, UserName userName, NickName nickName) {
        this.loginId = loginId;
        this.userName = userName;
        this.nickName = nickName;
    }

    public static SignUpResponse of(User user) {
        return SignUpResponse.builder()
                .loginId(user.getLoginId())
                .userName(user.getUserName())
                .nickName(user.getNickname())
                .build();
    }

}