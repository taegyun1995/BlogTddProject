package tdd.blogProject.user.application.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tdd.blogProject.user.application.port.in.AccessTokenCreateUseCase;
import tdd.blogProject.user.application.port.out.JwtTokenCreatePort;
import tdd.blogProject.user.domain.User;

@Component
public class AccessTokenCreateService implements AccessTokenCreateUseCase {

    @Value("${jwt.access_token_expiration_time}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;

    private final JwtTokenCreatePort jwtTokenCreatePort;

    public AccessTokenCreateService(JwtTokenCreatePort jwtTokenCreatePort) {
        this.jwtTokenCreatePort = jwtTokenCreatePort;
    }

    @Override
    public String createAccessToken(User user) {
        return jwtTokenCreatePort.createJwtToken(user, ACCESS_TOKEN_EXPIRATION_TIME);
    }

}