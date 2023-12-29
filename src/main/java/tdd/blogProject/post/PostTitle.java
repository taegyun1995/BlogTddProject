package tdd.blogProject.post;

import lombok.Getter;

@Getter
public class PostTitle {

    private final String value;

    public PostTitle(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("제목이 비어있습니다.");
        }
        this.value = value;
    }

}