package com.example.data_jpa_vintage;

import java.nio.file.Paths;
import java.util.Locale;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {

        WebMvcConfigurer.super.addResourceHandlers(registry);
        String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
        logger.info("resourcePath: " + resourcePath);
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(resourcePath);
    }

}
