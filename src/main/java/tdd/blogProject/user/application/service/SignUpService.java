package tdd.blogProject.user.application.service;

import org.springframework.stereotype.Service;
import tdd.blogProject.user.adapter.in.web.response.SignUpResponse;
import tdd.blogProject.user.application.port.in.AccessTokenCreateUseCase;
import tdd.blogProject.user.application.port.in.RefreshTokenCreateUseCase;
import tdd.blogProject.user.application.port.in.SignUpCommand;
import tdd.blogProject.user.application.port.in.SignUpUseCase;
import tdd.blogProject.user.application.port.out.SignUpPort;
import tdd.blogProject.user.domain.User;

@Service
public class SignUpService implements SignUpUseCase {

    private final SignUpPort signUpPort;
    private final AccessTokenCreateUseCase accessTokenCreateUseCase;
    private final RefreshTokenCreateUseCase refreshTokenCreateUseCase;

    public SignUpService(SignUpPort signUpPort, AccessTokenCreateUseCase accessTokenCreateUseCase, RefreshTokenCreateUseCase refreshTokenCreateUseCase) {
        this.signUpPort = signUpPort;
        this.accessTokenCreateUseCase = accessTokenCreateUseCase;
        this.refreshTokenCreateUseCase = refreshTokenCreateUseCase;
    }

    @Override
    public SignUpResponse signUp(String userAgent, SignUpCommand command) {
        User user = User.of(command);
        signUpPort.signUp(user);

        String accessToken = accessTokenCreateUseCase.createAccessToken(user);

        return SignUpResponse.of(user, accessToken);
    }

}