package com.github.peacetrue.module.modules.module;

import com.github.peacetrue.metadata.controller.ControllerMetadataAutoConfiguration;
import com.github.peacetrue.module.ControllerModuleAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xiayx
 */
@Configuration
@ImportAutoConfiguration(classes = {
        WebMvcAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
        ControllerMetadataAutoConfiguration.class,
        ControllerModuleAutoConfiguration.class,
        TestMybatisModuleAutoConfiguration.class
})
@PropertySource("classpath:application-module-controller-test.properties")
public class TestControllerModuleAutoConfiguration {
}
