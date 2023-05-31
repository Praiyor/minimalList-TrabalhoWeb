package com.dsw.trabalho.minimalList.helper;

import java.nio.file.Path;
import java.nio.file.Paths;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
public class ImageConfig implements WebMvcConfigurer {
 
 
	@Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("images/**").addResourceLocations("file:uploads/");
        registry.addResourceHandler("images/profile/**").addResourceLocations("file:uploads/");
    }
}
