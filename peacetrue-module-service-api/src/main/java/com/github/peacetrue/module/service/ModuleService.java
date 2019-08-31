package com.github.peacetrue.module.service;

import com.github.peacetrue.metadata.service.RecordInfo;

/**
 * @author xiayx
 */
public interface ModuleService {

    /** 判断记录是否存在 */
    boolean exists(RecordInfo recordInfo);

    /** 修改记录 */
    int modify(RecordInfo recordInfo, ModifyOptions options);

}
