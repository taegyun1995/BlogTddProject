package tdd.blogProject.user.application.port.in;

import tdd.blogProject.user.adapter.in.web.response.SignUpResponse;

public interface SignUpUseCase {

    SignUpResponse signUp(String userAgent, SignUpCommand command);

}