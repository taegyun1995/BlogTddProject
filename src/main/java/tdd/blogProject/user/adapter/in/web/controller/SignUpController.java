package tdd.blogProject.user.adapter.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdd.blogProject.user.adapter.in.web.response.SignUpResponse;
import tdd.blogProject.user.application.port.in.SignUpCommand;
import tdd.blogProject.user.application.port.in.SignUpUseCase;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1")
public class SignUpController {

    private final SignUpUseCase useCase;

    public SignUpController(SignUpUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(
            @RequestHeader("user-agent") String userAgent,
            @RequestBody SignUpCommand command
    ) {
        SignUpResponse response = useCase.signUp(userAgent, command);
        return ResponseEntity.status(CREATED).body(response);
    }

}