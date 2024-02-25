package tdd.blogProject.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import tdd.blogProject.user.domain.LoginId;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByLoginId(LoginId loginId);

}