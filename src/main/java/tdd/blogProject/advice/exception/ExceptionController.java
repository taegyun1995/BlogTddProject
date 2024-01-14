package tdd.blogProject.advice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tdd.blogProject.advice.exception.custom.BadRequestException;
import tdd.blogProject.advice.exception.custom.LengthRequiredException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundUserException(BadRequestException businessException) {
        return ResponseEntity.status(LENGTH_REQUIRED).body(new ExceptionResponse(businessException.getExceptionCodeConst()));
    }

    @ExceptionHandler(LengthRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundUserException(LengthRequiredException businessException) {
        return ResponseEntity.status(LENGTH_REQUIRED).body(new ExceptionResponse(businessException.getExceptionCodeConst()));
    }

}