package com.example.data_jpa_vintage;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/almacen/**")
                .addResourceLocations("file:/C:/Users/roberto.bravo/Desktop/proy/curso-springboot/almacen/");
    }

}
