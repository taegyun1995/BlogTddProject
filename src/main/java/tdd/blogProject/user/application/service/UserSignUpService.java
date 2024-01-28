package tdd.blogProject.user.application.service;

import org.springframework.stereotype.Service;
import tdd.blogProject.user.adapter.in.web.SignUpResponse;
import tdd.blogProject.user.application.port.in.UserSignUpCommand;
import tdd.blogProject.user.application.port.in.UserSignUpUserCase;
import tdd.blogProject.user.application.port.out.UserSignUpPort;

@Service
public class UserSignUpService implements UserSignUpUserCase {

    private UserSignUpPort userSignUpPort;

    public UserSignUpService(UserSignUpPort userSignUpPort) {
        this.userSignUpPort = userSignUpPort;
    }

    @Override
    public SignUpResponse signUp(UserSignUpCommand command) {
        userSignUpPort.signUp(command);

        return new SignUpResponse(command.getLoginId().getValue());
    }

}