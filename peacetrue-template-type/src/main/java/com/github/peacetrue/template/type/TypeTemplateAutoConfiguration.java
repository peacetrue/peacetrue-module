package com.github.peacetrue.template.type;

import com.github.peacetrue.spring.web.method.support.ConcreteClassAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(TypeTemplateProperties.class)
@ConditionalOnClass(ConcreteClassAutoConfiguration.class)
@AutoConfigureBefore(ConcreteClassAutoConfiguration.class)
@PropertySource("classpath:peacetrue-template-type.properties")
public class TypeTemplateAutoConfiguration {

}
