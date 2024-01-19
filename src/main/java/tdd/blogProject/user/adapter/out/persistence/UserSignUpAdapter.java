package tdd.blogProject.user.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import tdd.blogProject.user.application.port.in.UserSignUpCommand;
import tdd.blogProject.user.application.port.out.UserSignUpPort;
import tdd.blogProject.user.domain.UserName;

@Repository
public class UserSignUpAdapter implements UserSignUpPort {

    private final JpaUserRepository repository;

    public UserSignUpAdapter(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void signUp(UserSignUpCommand command) {
        UserEntity userEntity = createDummyUser();

        repository.save(userEntity);
    }

    private UserEntity createDummyUser() {
        UserName dummyUserName = new UserName("dummyUser");
        return UserEntity.builder()
                .userName(dummyUserName)
                .build();
    }

}