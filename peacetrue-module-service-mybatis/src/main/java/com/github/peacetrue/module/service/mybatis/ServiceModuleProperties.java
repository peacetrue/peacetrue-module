package com.github.peacetrue.module.service.mybatis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Set;

/**
 * @author xiayx
 */
@Data
@ConfigurationProperties(prefix = "peacetrue.module")
public class ServiceModuleProperties {

    /** 修改时忽略的属性 */
    private Set<String> ignoredPropertiesWhenModify = Collections.singleton("id");

}
