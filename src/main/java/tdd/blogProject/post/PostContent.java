package tdd.blogProject.post;

import lombok.Getter;

@Getter
public class PostContent {

    private final String value;

    public PostContent(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("내용이 비어있습니다.");
        }
        this.value = value;
    }

}