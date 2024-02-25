package tdd.blogProject.user.application.port.in;

import tdd.blogProject.user.domain.User;

public interface RefreshTokenCreateUseCase {

    String createRefreshToken(User user);

}