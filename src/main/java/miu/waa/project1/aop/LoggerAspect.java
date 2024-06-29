package miu.waa.project1.aop;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.model.Logger;
import miu.waa.project1.repository.LoggerRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggerAspect {
    private final LoggerRepository loggerRepository;

    @Around("execution(* miu.waa.project1.service.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Logger logger = new Logger();
        logger.setMethod(joinPoint.getSignature().getName());
        logger.setMessage("Method " + joinPoint.getSignature().getName() + " is called");
        logger.setParameters(joinPoint.getArgs().toString());
        var result = joinPoint.proceed();
        logger.setDuration((int) (System.currentTimeMillis() - start));
        loggerRepository.save(logger);
        return result;
    }
}
