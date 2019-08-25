package com.github.peacetrue.template.modules.demo;

import com.github.peacetrue.core.OperatorCapableImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author xiayx
 */
@Getter
@Setter
@ToString(callSuper = true)
public class DemoAdd<OperatorId> extends OperatorCapableImpl<OperatorId> {

    private static final long serialVersionUID = 0L;

    @NotNull
    @Size(min = 1, max = 255)
    private String code;
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

}
