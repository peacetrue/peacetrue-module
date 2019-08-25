package com.github.peacetrue.template;

import com.github.peacetrue.template.modules.demo.DemoController;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(ControllerTemplateProperties.class)
@PropertySource("classpath:/application-template-controller.properties")
public class ControllerTemplateAutoConfiguration {

    @Bean
    public DemoController demoController() {
        return new DemoController();
    }
}
