package com.waa.project.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FeedbackHandlerAspect {

    @Before("execution(* com.waa.project.controller.feedback.FeedbackController.*(..))")
    public void beforeMe(JoinPoint joinPoint) {
        System.out.println("Execution Before JoingPoint == " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.waa.project.controller.feedback.FeedbackController.*(..))")
    public void afterMe(JoinPoint joinPoint) {
        System.out.println("Execution After JoingPoint == " + joinPoint.getSignature().getName());
    }

}
