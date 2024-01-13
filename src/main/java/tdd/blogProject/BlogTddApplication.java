package tdd.blogProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class BlogTddApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogTddApplication.class, args);
    }

}