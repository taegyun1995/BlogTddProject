package tdd.blogProject.advice.exception.custom;

import tdd.blogProject.advice.exception.BusinessException;
import tdd.blogProject.advice.exception.ExceptionCodeConst;

public class LengthRequiredException extends BusinessException {

    public LengthRequiredException(ExceptionCodeConst exceptionCodeConst) {
        super(exceptionCodeConst);
    }

}