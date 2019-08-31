package com.github.peacetrue.module;

import com.github.peacetrue.metadata.controller.ControllerMetadataAutoConfiguration;
import com.github.peacetrue.symbol.CombinedSymbolResolver;
import com.github.peacetrue.symbol.ParamSymbolResolver;
import com.github.peacetrue.symbol.PathSymbolResolver;
import com.github.peacetrue.symbol.SymbolResolver;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xiayx
 */
@Configuration
@AutoConfigureBefore(ControllerMetadataAutoConfiguration.class)
@EnableConfigurationProperties(ControllerModuleProperties.class)
@PropertySource("classpath:/application-module-controller.properties")
public class ControllerModuleAutoConfiguration {

    private ControllerModuleProperties properties;

    public ControllerModuleAutoConfiguration(ControllerModuleProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean(CommonModuleController.class)
    public CommonModuleController commonModuleController() {
        return new CommonModuleController();
    }

    @Bean
    public com.github.peacetrue.metadata.controller.ModuleNameResolver moduleNameResolverProxy() {
        return request -> moduleNameResolver().resolveSymbol(request);
    }

    @Bean
    public SymbolResolver<String> moduleNameResolver() {
        return new ModuleNameResolver(new CombinedSymbolResolver<>(
                new ParamSymbolResolver(properties.getParamName()),
                new ModuleNameResolver(new PathSymbolResolver(properties.getPathIndex()))
        ));
    }

}
