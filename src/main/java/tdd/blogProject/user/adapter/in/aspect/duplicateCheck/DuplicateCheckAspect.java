package tdd.blogProject.user.adapter.in.aspect.duplicateCheck;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import tdd.blogProject.user.adapter.out.persistence.JpaUserRepository;
import tdd.blogProject.user.domain.User;

@Aspect
@Component
public class DuplicateCheckAspect {

    private final JpaUserRepository jpaUserRepository;

    public DuplicateCheckAspect(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Before("execution(* tdd.blogProject.user.adapter.out.persistence.SignUpAdapter.signUp(..)) && args(user)")
    public void beforeSignUp(User user) {
        if (jpaUserRepository.existsByLoginId(user.getLoginId())) {
            throw new RuntimeException("이미 존재하고 있는 유저입니다.");
        }
    }

}