package com.github.peacetrue.module.service.support;

import com.github.peacetrue.module.service.ModifyOptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Set;

/**
 * @author xiayx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyOptionsImpl implements ModifyOptions {

    public static final ModifyOptionsImpl DEFAULT = new ModifyOptionsImpl(Collections.emptySet(), Collections.emptySet());

    private Set<String> ignoredProperties;
    private Set<String> notIgnoredNullProperties;

}
