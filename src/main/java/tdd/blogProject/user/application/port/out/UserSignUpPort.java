package tdd.blogProject.user.application.port.out;

import tdd.blogProject.user.application.port.in.UserSignUpCommand;

public interface UserSignUpPort {

    void signUp(UserSignUpCommand command);

}