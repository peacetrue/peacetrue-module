package com.github.peacetrue.template.modules.demo;

import com.github.peacetrue.result.exception.ResultException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nullable;

/**
 * @author xiayx
 */
public interface DemoService {

    /** 新增 */
    <Id, OperatorId> DemoVO<Id, OperatorId> add(DemoAdd<OperatorId> params) throws ResultException;

    /** 分页查询 */
    <Id, OperatorId> Page<DemoVO<Id, OperatorId>> query(@Nullable DemoQuery params, Pageable pageable) throws ResultException;

    /** 获取 */
    <Id, OperatorId> DemoVO<Id, OperatorId> get(DemoGet<Id, OperatorId> params) throws ResultException;

    /** 修改 */
    <Id, OperatorId> int modify(DemoModify<Id, OperatorId> params) throws ResultException;

    /** 删除 */
    <Id, OperatorId> int delete(DemoDelete<Id, OperatorId> params) throws ResultException;
}
