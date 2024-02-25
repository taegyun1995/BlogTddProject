package tdd.blogProject.user.application.port.out;

import tdd.blogProject.user.domain.User;

public interface JwtTokenCreatePort {

    String createJwtToken(User user, long expirationTime);

}