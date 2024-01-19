package tdd.blogProject.user.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class LoginId {

    private String value;

    public LoginId(String value) {
        this.value = value;
    }

}