package com.github.peacetrue.module.service;

import com.github.peacetrue.metadata.service.RecordInfo;

import java.util.Collections;
import java.util.Set;

/**
 * 修改选项
 *
 * @author xiayx
 * @see ModuleService#modify(RecordInfo, ModifyOptions)
 */
public interface ModifyOptions {

    /** 获取修改时总是忽略的属性，有一些属性总是不需要修改的，例如：主键、创建者、创建时间等 */
    default Set<String> getIgnoredProperties() {
        return Collections.singleton("id");
    }

    /** 修改时会跳过值为{@code null}的属性，如果需要强制更新为{@code null}，可配置此属性 */
    default Set<String> getNotIgnoredNullProperties() {
        return Collections.emptySet();
    }
}
