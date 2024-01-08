package tdd.blogProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tdd.blogProject.aspect.LogAspect;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

}