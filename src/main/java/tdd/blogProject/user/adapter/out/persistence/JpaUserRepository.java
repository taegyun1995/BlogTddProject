package tdd.blogProject.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import tdd.blogProject.user.domain.LoginId;
import tdd.blogProject.user.domain.UserName;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByLoginId(LoginId loginId);

    Optional<UserEntity> findByUserName(UserName userName);

}