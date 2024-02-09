package tdd.blogProject.user.domain;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.advice.exception.custom.BadRequestException;
import tdd.blogProject.advice.exception.custom.LengthRequiredException;
import tdd.blogProject.user.application.port.in.UserSignUpCommand;

import java.util.regex.Pattern;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class Nickname {

    private static final Pattern PATTERN = Pattern.compile("^.{1,15}$");

    @Column(name = "nickname")
    private String value;

    public Nickname(String value) {
        this.value = value;
    }

    public static Nickname of(UserSignUpCommand command) {
        validate(command.getNickname().value);
        return new Nickname(command.getNickname().value);
    }

    private static void validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new BadRequestException(null);
        }
        if (!PATTERN.matcher(value).matches()) {
            throw new LengthRequiredException(null);
        }
    }

}