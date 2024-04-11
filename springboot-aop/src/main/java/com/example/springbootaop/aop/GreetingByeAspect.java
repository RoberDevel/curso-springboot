package com.example.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingByeAspect {

    // private Logger logger = LoggerFactory.getLogger(GreetingAspect.class);
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
     * @Pointcut("execution(* com.example.springbootaop.service.IGreetingService.sayHello(..)) || execution(* com.example.springbootaop.service.IGreetingService.sayHelloError(..))"
     * )
     * private void byeAspectPointcut() {
     * }
     */

    // El * significa cualquier tipo de retorno del metodo, puedes poner String, los
    // .. son los argumentos
    // el 2o asterisco significara que se aplicara a cualquier clase del package
    // service, y el 3o, a cualquier metodo
    // @Before(value = "execution(* com.example.springbootaop.service.*.*(..))")
    @Before("GreetingServicePointcut.greetingAspectPointcut() || GreetingServicePointcut.byeAspectPointcut()")
    public void loggerBefore(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes: " + method + " con los argumentos " + args
                + " en el aspecto GreetingAspect con orden 2 (@Order(2))");

    }

    @After(value = "GreetingServicePointcut.greetingAspectPointcut() || GreetingServicePointcut.byeAspectPointcut()")
    public void loggerAfter(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues: " + method + "() con los argumentos " + args
                + " en el aspecto GreetingAspect con orden 2 (@Order(2))");

    }

    @AfterReturning("GreetingServicePointcut.greetingAspectPointcut() || GreetingServicePointcut.byeAspectPointcut()")
    public void loggerAfterReturning(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues de retornar: " + method + "() con los argumentos " + args);

    }

    @AfterThrowing("GreetingServicePointcut.greetingAspectPointcut() || GreetingServicePointcut.byeAspectPointcut()")
    public void loggerAfterThorwing(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues de lanzar la excepcion: " + method + "() con los argumentos " + args);

    }

    @Around("GreetingServicePointcut.greetingAspectPointcut() || GreetingServicePointcut.byeAspectPointcut()")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {
            logger.info("En el around con el metodo: " + method + "() con los argumentos " + args);
            result = joinPoint.proceed();
            logger.info("En el around con el metodo: " + method + "() con el resultado " + result);
            return result;
        } catch (Throwable e) {
            logger.error("En el around: error en la llamada del metodo: " + method + "()");
            throw e;
        }

    }

}