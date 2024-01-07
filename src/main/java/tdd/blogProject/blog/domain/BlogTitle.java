package tdd.blogProject.blog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.blog.application.port.in.update.BlogUpdateCommand;

import java.util.regex.Pattern;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class BlogTitle {

    private static final Pattern PATTERN = Pattern.compile("^.{1,20}$");

    @Column(name = "blog_title")
    private String title;

    public BlogTitle(String title) {
        this.title = title;
    }

    public static BlogTitle of(BlogUpdateCommand command) {
        validate(command.getBlogTitle().getTitle());
        return new BlogTitle(command.getBlogTitle().getTitle());
    }

    private static void validate(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("블로그 제목이 비어있습니다.");
        }
        if (!PATTERN.matcher(title).matches()) {
            throw new IllegalArgumentException("블로그 제목은 1자 이상 20자 이하의 문자열이어야 합니다.");
        }
    }

}