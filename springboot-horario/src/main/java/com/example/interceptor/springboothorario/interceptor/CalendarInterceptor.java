package com.example.interceptor.springboothorario.interceptor;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("calendarInterceptor")
public class CalendarInterceptor implements HandlerInterceptor {

    @Value("${config.calendar.open}")
    private Integer open;
    @Value("${config.calendar.close}")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour < open || hour >= close) {
            // response.sendRedirect("/closed");
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> data = new HashMap<>();
            StringBuilder message = new StringBuilder("Estamos cerrados. Por favor visítenos desde las ")
                    .append(open).append(" hasta las ").append(close).append(" horas.");
            data.put("message", message.toString());
            data.put("time", new Date().toString());
            data.put("tittle", "Fuera de horario de atención al cliente.");
            response.setContentType("application/json");
            response.setStatus(401);
            response.getWriter().write(mapper.writeValueAsString(data));
            // response.setCharacterEncoding("UTF-8");
            return false;
        }
        StringBuilder message = new StringBuilder("Bienvenidos al horario de atención al cliente.")
                .append(" Atendemos desde las ")
                .append(open)
                .append(" hasta las ")
                .append(close).append(" horas.");
        request.setAttribute("message", message.toString());
        // request.setCharacterEncoding("UTF-8");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

}
