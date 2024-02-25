package tdd.blogProject.user.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import tdd.blogProject.user.application.port.out.SignUpPort;
import tdd.blogProject.user.domain.User;

@Repository
public class SignUpAdapter implements SignUpPort {

    private final JpaUserRepository jpaUserRepository;

    public SignUpAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void signUp(User user) {
        UserEntity userEntity = UserEntity.of(user);
        jpaUserRepository.save(userEntity);
    }

}