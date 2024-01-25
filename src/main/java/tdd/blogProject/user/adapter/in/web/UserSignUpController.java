package tdd.blogProject.user.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tdd.blogProject.advice.success.SuccessResponse;
import tdd.blogProject.user.application.port.in.UserSignUpCommand;
import tdd.blogProject.user.application.port.in.UserSignUpUserCase;

import static org.springframework.http.HttpStatus.CREATED;
import static tdd.blogProject.advice.success.SuccessCodeConst.SIGNUP_SUCCESS;

@RestController
public class UserSignUpController {

    private UserSignUpUserCase useCase;

    public UserSignUpController(UserSignUpUserCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/api/v1/user")
    public ResponseEntity<SuccessResponse> signUp(@RequestBody UserSignUpCommand command) {
        useCase.signUp(command);
        return ResponseEntity.status(CREATED).body(new SuccessResponse(SIGNUP_SUCCESS));
    }

}