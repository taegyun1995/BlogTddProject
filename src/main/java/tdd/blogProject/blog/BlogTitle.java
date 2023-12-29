package tdd.blogProject.blog;

import lombok.Getter;

@Getter
public class BlogTitle {

    private final String value;

    public BlogTitle(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("제목이 비어있습니다.");
        }
        this.value = value;
    }

}