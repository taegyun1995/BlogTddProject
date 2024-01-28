package tdd.blogProject.user.adapter.in.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class SignUpResponse {

    private String loginId;

    public SignUpResponse(String loginId) {
        this.loginId = loginId;
    }

}