package com.github.peacetrue.template.modules.demo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiayx
 */
@Data
public class DemoVO<Id, OperatorId> implements Serializable {

    private static final long serialVersionUID = 0L;

    private Id id;
    private String code;
    private String name;
    private OperatorId creatorId;
    private Date createdTime;
    private OperatorId modifierId;
    private Date modifiedTime;
}
