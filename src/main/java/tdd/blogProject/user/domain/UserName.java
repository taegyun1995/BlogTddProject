package tdd.blogProject.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.advice.exception.ExceptionCodeConst;
import tdd.blogProject.advice.exception.custom.BadRequestException;

import java.util.Objects;
import java.util.regex.Pattern;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class UserName {

    private static final Pattern PATTERN = Pattern.compile("^[0-9a-zA-Z]{4,16}$");

    @Column(name = "user_name")
    private String value;

    public UserName(String value) {
        this.value = value;
    }

    public static UserName of(String value) {
        validate(value);
        return new UserName(value);
    }

    private static void validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new BadRequestException(ExceptionCodeConst.BAD_REQUEST_TITLE);
        }
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("유저 이름 정규표현식 메시지 미정");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserName userName = (UserName) o;
        return Objects.equals(getValue(), userName.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

}