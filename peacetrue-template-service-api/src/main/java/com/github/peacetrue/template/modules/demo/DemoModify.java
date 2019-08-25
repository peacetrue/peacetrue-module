package com.github.peacetrue.template.modules.demo;

import com.github.peacetrue.core.OperatorCapableImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xiayx
 */
@Getter
@Setter
@ToString(callSuper = true)
public class DemoModify<Id, OperatorId> extends OperatorCapableImpl<OperatorId> {

    private static final long serialVersionUID = 0L;

    private Id id;
    private String code;
    private String name;
}
