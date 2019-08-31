package com.github.peacetrue.module.service.mybatis;

import com.github.peacetrue.module.service.ModuleService;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Objects;

/**
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(ServiceModuleProperties.class)
@MapperScan(basePackageClasses = ServiceModuleAutoConfiguration.class, annotationClass = Mapper.class)
@PropertySource("classpath:application-module-service.properties")
public class ServiceModuleAutoConfiguration {

    private ServiceModuleProperties properties;

    public ServiceModuleAutoConfiguration(ServiceModuleProperties properties) {
        this.properties = Objects.requireNonNull(properties);
    }

    @Bean(name = "moduleService0")
    public ModuleService moduleService() {
        return new ModuleServiceImpl(properties.getIgnoredPropertiesWhenModify());
    }


}
