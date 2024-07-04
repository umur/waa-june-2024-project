package universityconnect.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* universityconnect.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executing: " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* universityconnect.service..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method executed: " + joinPoint.getSignature().toShortString());
        logger.info("Return value: " + result);
    }
}

