package tdd.blogProject.user.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class Password {

    private String value;

    public Password(String value) {
        this.value = value;
    }

}