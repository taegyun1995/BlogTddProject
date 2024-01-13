package tdd.blogProject.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* tdd.blogProject..*.*(..))")
    public void inBackendPackage() {
    }

    @AfterThrowing(pointcut = "inBackendPackage()", throwing = "ex")
    public void handleException(JoinPoint joinPoint, Exception ex) {
        log.error(
                "An error occurred while executing method: {} " +
                        "in class: {} " +
                        "with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getArgs(),
                ex
        );
    }
}
