package tdd.blogProject.user.application.port.in;

import tdd.blogProject.user.adapter.in.web.SignUpResponse;

public interface UserSignUpUserCase {

    SignUpResponse signUp(UserSignUpCommand command);

}