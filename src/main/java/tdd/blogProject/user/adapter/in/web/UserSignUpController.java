package tdd.blogProject.user.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tdd.blogProject.user.application.port.in.UserSignUpCommand;
import tdd.blogProject.user.application.port.in.UserSignUpUserCase;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class UserSignUpController {

    private UserSignUpUserCase useCase;

    public UserSignUpController(UserSignUpUserCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/api/v1/user")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody UserSignUpCommand command) {
        SignUpResponse response = useCase.signUp(command);
        return ResponseEntity.status(CREATED).body(response);
    }

}