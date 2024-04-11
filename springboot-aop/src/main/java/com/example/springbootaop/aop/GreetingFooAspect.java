package com.example.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//En el before es el primero que se ejecuta, pero para el after es el ultimo en ejecutarse
//order(1) envuelve al resto, por eso en el before es primero pero en el after es el ultimo
@Order(1)
@Aspect
@Component
public class GreetingFooAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // En lugar de poner execution(*
    // com.example.springbootaop.service.IGreetingService.sayHello(..)) en el metodo
    // @Before o @After etc, creas un metodo con la anotacion @Pointcut y lo llamas
    // en el metodo @Before o @After etc

    @Before("GreetingServicePointcut.greetingFooAspectPointcut()")
    public void loggerBefore(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes: " + method + " con los argumentos " + args
                + " en el aspecto GreetingFooAspect con orden 1 (@Order(1))");

    }

    @After("GreetingServicePointcut.greetingFooAspectPointcut()")
    public void loggerAfter(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues: " + method + "() con los argumentos " + args
                + " en el aspecto GreetingFooAspect con orden 1 (@Order(1))");

    }

}
