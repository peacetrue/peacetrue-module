package com.github.peacetrue.module;

import com.github.peacetrue.spring.formatter.date.AutomaticDateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author xiayx
 */
@SpringBootApplication
public class ModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleApplication.class, args);
    }

    @Bean
    public AutomaticDateFormatter dateFormatter() {
        return new AutomaticDateFormatter();
    }

    @Bean
    public WebMvcConfigurer corsWebMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(true)
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("*");
            }
        };
    }

    @Bean
    public HttpMessageConverters httpMessageConverters(@Autowired List<HttpMessageConverter<?>> httpMessageConverters) {
        return new HttpMessageConverters(false, httpMessageConverters);
    }

    @ControllerAdvice
    public static class StringTrimmerControllerAdvice {
        @InitBinder
        public void registerCustomEditors(WebDataBinder binder) {
            // configure for empty string change to null
            binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        }
    }
}
