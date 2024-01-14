package tdd.blogProject.advice.exception.custom;

import lombok.Getter;
import tdd.blogProject.advice.exception.BusinessException;
import tdd.blogProject.advice.exception.ExceptionCodeConst;

@Getter
public class BadRequestException extends BusinessException {

    public BadRequestException(ExceptionCodeConst exceptionCodeConst) {
        super(exceptionCodeConst);
    }

}