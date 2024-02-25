package tdd.blogProject.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.advice.exception.custom.BadRequestException;
import tdd.blogProject.advice.exception.custom.LengthRequiredException;
import tdd.blogProject.user.application.port.in.SignUpCommand;

import java.util.Objects;
import java.util.regex.Pattern;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class NickName {

    private static final Pattern PATTERN = Pattern.compile("^.{1,15}$");

    @Column(name = "nick_name")
    private String value;

    public NickName(String value) {
        this.value = value;
    }

    public static NickName of(SignUpCommand command) {
        validate(command.getNickname().value);
        return new NickName(command.getNickname().value);
    }

    private static void validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new BadRequestException(null);
        }
        if (!PATTERN.matcher(value).matches()) {
            throw new LengthRequiredException(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NickName nickName = (NickName) o;
        return Objects.equals(getValue(), nickName.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

}