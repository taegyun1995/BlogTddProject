package tdd.blogProject.user.application.port.in;

import tdd.blogProject.user.domain.User;

public interface AccessTokenCreateUseCase {

    String createAccessToken(User user);

}