package tdd.blogProject.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.advice.exception.custom.BadRequestException;
import tdd.blogProject.advice.exception.custom.LengthRequiredException;
import tdd.blogProject.user.application.port.in.UserSignUpCommand;

import java.util.regex.Pattern;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class Password {

    private static final Pattern PATTERN = Pattern.compile("^.{1,15}$");

    @Column(name = "password")
    private String value;

    public Password(String value) {
        this.value = value;
    }

    public static LoginId of(UserSignUpCommand command) {
        validate(command.getPassword().value);
        return new LoginId(command.getPassword().value);
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