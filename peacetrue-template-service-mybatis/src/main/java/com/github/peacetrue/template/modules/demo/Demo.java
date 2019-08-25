package com.github.peacetrue.template.modules.demo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiayx
 */
@Getter
@Setter
public class Demo<Id, OperatorId> implements Serializable {

    private static final long serialVersionUID = 0L;

    private Id id;
    private String code;
    private String name;
    private OperatorId creatorId;
    private Date createdTime;
    private OperatorId modifierId;
    private Date modifiedTime;
}
