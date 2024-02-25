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
public class Password {

    private static final Pattern PATTERN = Pattern.compile("^.{1,15}$");

    @Column(name = "password")
    private String value;

    public Password(String value) {
        this.value = value;
    }

    public static LoginId of(SignUpCommand command) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(getValue(), password.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

}