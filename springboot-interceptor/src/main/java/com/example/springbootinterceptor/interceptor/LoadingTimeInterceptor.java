package com.example.springbootinterceptor.interceptor;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("loadingTimeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        HandlerMethod controller = ((HandlerMethod) handler);
        log.info("LoadingTimeInterceptor: preHandle() entrando.. " + controller.getMethod().getName());
        Random random = new Random();
        Integer delay = random.nextInt(1000);
        Thread.sleep(delay);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        Long endTime = System.currentTimeMillis();
        Long startTime = (Long) request.getAttribute("startTime");
        Long total = endTime - startTime;
        log.info("LoadingTimeInterceptor: postHandle() saliendo.. " + ((HandlerMethod) handler).getMethod().getName()
                + ", tiempo total: " + total + "ms");
    }
}
