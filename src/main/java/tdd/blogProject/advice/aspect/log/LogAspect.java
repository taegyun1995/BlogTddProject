package tdd.blogProject.advice.aspect.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* tdd.blogProject..*.*(..))")
    protected void inBackendPackage() {
    }

    @Pointcut("execution(* tdd.blogProject..*.*Controller.*(..))")
    protected void controller() {
    }

    @Before(value = "controller()")
    protected void logBeforeRequest(JoinPoint joinPoint) {
        log.info("### Start request {}", joinPoint.getSignature().toShortString());
        log.info("Method arguments: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "controller()", returning = "returnValue")
    protected void logAfterReturning(JoinPoint joinPoint, Object returnValue) {
        log.info("### End response {}", joinPoint.getSignature().toShortString());
        log.info("Method return value: {}", returnValue);
    }

    @AfterThrowing(pointcut = "inBackendPackage()", throwing = "e")
    protected void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        log.error(
                "An error occurred while executing method: {} "
                        + "in class: {} "
                        + "with arguments: {}. "
                        + "Error message: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getTarget().getClass().getName(),
                Arrays.toString(joinPoint.getArgs()),
                e.getMessage()
        );
    }


}