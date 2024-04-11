package com.example.springbootaop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointcut {

    @Pointcut("execution(* com.example.springbootaop.service.IGreetingService.*(..))")
    void greetingAspectPointcut() {
    }

    @Pointcut("execution(* com.example.springbootaop.service.IByeService.*(..))")
    void byeAspectPointcut() {
    }

    @Pointcut("execution(* com.example.springbootaop.service.IGreetingService.sayHello(..))")
    void greetingFooAspectPointcut() {
    }

}
