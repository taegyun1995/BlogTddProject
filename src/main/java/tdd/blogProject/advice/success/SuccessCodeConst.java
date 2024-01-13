package tdd.blogProject.advice.success;

import lombok.Getter;

@Getter
public enum SuccessCodeConst {

    SIGNUP_SUCCESS(201, "SIGNUP_SUCCESS", "회원가입에 성공했습니다."),
    BLOG_CREATE_SUCCESS(201, "BLOG_CREATE_SUCCESS", "블로그가 생성되었습니다.");

    private final int status;
    private final String code;
    private final String message;

    SuccessCodeConst(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}