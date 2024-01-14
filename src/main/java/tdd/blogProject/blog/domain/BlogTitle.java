package tdd.blogProject.blog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tdd.blogProject.advice.exception.custom.BadRequestException;
import tdd.blogProject.advice.exception.custom.LengthRequiredException;
import tdd.blogProject.blog.application.port.in.BlogUpdateCommand;

import java.util.Objects;
import java.util.regex.Pattern;

import static lombok.AccessLevel.PROTECTED;
import static tdd.blogProject.advice.exception.ExceptionCodeConst.BAD_REQUEST_TITLE;
import static tdd.blogProject.advice.exception.ExceptionCodeConst.LENGTH_REQUIRED_TITLE;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class BlogTitle {

    private static final Pattern PATTERN = Pattern.compile("^.{1,20}$");

    @Column(name = "blog_title")
    private String value;

    public BlogTitle(String value) {
        validate(value);
        this.value = value;
    }

    public static BlogTitle of(BlogUpdateCommand command) {
        validate(command.getBlogTitle().getValue());
        return new BlogTitle(command.getBlogTitle().getValue());
    }

    private static void validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new BadRequestException(BAD_REQUEST_TITLE);
        }
        if (!PATTERN.matcher(value).matches()) {
            throw new LengthRequiredException(LENGTH_REQUIRED_TITLE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogTitle blogTitle = (BlogTitle) o;
        return Objects.equals(value, blogTitle.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}