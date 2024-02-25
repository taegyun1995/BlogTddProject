package tdd.blogProject.user.application.port.out;

import tdd.blogProject.user.domain.User;

public interface SignUpPort {

    void signUp(User user);

}