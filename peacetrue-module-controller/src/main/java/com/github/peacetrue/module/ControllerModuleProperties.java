package com.github.peacetrue.module;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 控制器配置
 *
 * @author xiayx
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "peacetrue.module")
public class ControllerModuleProperties {

    private String paramName = "moduleName";
    private Integer pathIndex = 1;
    /** 模块链接 */
    private Urls urls;

    @Data
    public static class Urls {
        private String basePath = "/common/*";
        /** 新增地址 */
        private String add = "";
        /** 查询地址 */
        private String query = "";
        /** 查看地址 */
        private String get = "";
        /** 查看地址 */
        private String exists = "/exists";
        /** 修改地址 */
        private String modify = "";
        /** 删除地址 */
        private String delete = "";
    }

}