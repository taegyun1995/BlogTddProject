package tdd.blogProject.advice.exception;

import lombok.Getter;

@Getter
public enum ExceptionCodeConst {

    LENGTH_REQUIRED_TITLE(416, "LENGTH_REQUIRED_TITLE", "블로그 제목은 1자 이상 20자 이하의 문자열이어야 합니다.");

    private final int status;
    private final String code;
    private final String message;

    ExceptionCodeConst(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}