package com.github.peacetrue.module.modules.module;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.github.peacetrue.metadata.service.ServiceMetadataAutoConfiguration;
import com.github.peacetrue.module.service.mybatis.ServiceModuleAutoConfiguration;
import com.github.peacetrue.template.modules.demo.Demo;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xiayx
 */
@Configuration
@ImportAutoConfiguration({
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        MybatisAutoConfiguration.class,
        PageHelperAutoConfiguration.class,
        ServiceMetadataAutoConfiguration.class,
        ServiceModuleAutoConfiguration.class,
})
@PropertySource("classpath:application-module-service-test.properties")
public class TestMybatisModuleAutoConfiguration {

    public static class ls_Demo extends Demo<Long, String> {}

}
