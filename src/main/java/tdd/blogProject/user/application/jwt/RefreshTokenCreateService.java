package tdd.blogProject.user.application.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tdd.blogProject.user.application.port.in.RefreshTokenCreateUseCase;
import tdd.blogProject.user.application.port.out.JwtTokenCreatePort;
import tdd.blogProject.user.domain.User;

@Component
public class RefreshTokenCreateService implements RefreshTokenCreateUseCase {

    @Value("${jwt.refresh_token_expiration_time}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;

    private final JwtTokenCreatePort jwtTokenCreatePort;

    public RefreshTokenCreateService(JwtTokenCreatePort jwtTokenCreatePort) {
        this.jwtTokenCreatePort = jwtTokenCreatePort;
    }

    @Override
    public String createRefreshToken(User user) {
        return jwtTokenCreatePort.createJwtToken(user, REFRESH_TOKEN_EXPIRATION_TIME);
    }

}