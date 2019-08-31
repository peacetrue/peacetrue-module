package com.github.peacetrue.module;

import com.github.peacetrue.metadata.service.RecordInfo;
import com.github.peacetrue.module.service.ModuleService;
import com.github.peacetrue.module.service.support.ModifyOptionsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author xiayx
 */
@RequestMapping(value = "${peacetrue.module.urls.base-path}" /*consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE}*/)
public class CommonModuleController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ModuleService moduleService;

    @ResponseBody
    @GetMapping(value = "${peacetrue.module.urls.exists}")
    public boolean exists(RecordInfo params) {
        logger.info("判断记录[{}]是否存在", params);
        return moduleService.exists(params);
    }

    /** 和{@link #exists(RecordInfo)}逻辑相同，便于前端唯一性验证使用 */
    @ResponseBody
    @GetMapping(value = "${peacetrue.module.urls.unique}")
    public boolean unique(RecordInfo params) {
        logger.info("判断记录[{}]是否唯一的", params);
        return !moduleService.exists(params);
    }

    @ResponseBody
    @PatchMapping(value = "${peacetrue.module.urls.modify}")
    public int modify(RecordInfo params, ModifyOptionsImpl options) {
        logger.info("修改记录[{}]", params);
        return moduleService.modify(params, options);
    }


}
