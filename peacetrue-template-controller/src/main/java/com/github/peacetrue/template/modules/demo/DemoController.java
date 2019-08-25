package com.github.peacetrue.template.modules.demo;

import com.github.peacetrue.spring.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


/**
 * @author xiayx
 */
@RequestMapping
@SuppressWarnings("unchecked")
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DemoService demoService;

    @ResponseBody
    @PostMapping(value = "${peacetrue.template.demo.urls.add}")
    public DemoVO add(DemoAdd params) {
        logger.info("新增信息[{}]", params);
        return demoService.add(BeanUtils.map(params, DemoAdd.class));
    }

    @ResponseBody
    @GetMapping(value = "${peacetrue.template.demo.urls.query}", params = "page")
    public Page<DemoVO<Object, Object>> query(DemoQuery params,
                                              @PageableDefault(sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable) {
        logger.info("分页查询信息[{}]", params);
        return demoService.query(BeanUtils.map(params, DemoQuery.class), pageable);
    }

    @ResponseBody
    @GetMapping(value = "${peacetrue.template.demo.urls.get}", params = {"!page"})
    public DemoVO get(DemoGet params) {
        logger.info("获取信息[{}]详情", params);
        return demoService.get(BeanUtils.map(params, DemoGet.class));
    }

    @ResponseBody
    @PutMapping(value = "${peacetrue.template.demo.urls.modify}")
    public int modify(DemoModify params) {
        logger.info("修改信息[{}]", params);
        return demoService.modify(BeanUtils.map(params, DemoModify.class));
    }

    @ResponseBody
    @DeleteMapping(value = "${peacetrue.template.demo.urls.delete}")
    public int delete(DemoDelete params) {
        logger.info("删除信息[{}]", params);
        return demoService.delete(BeanUtils.map(params, DemoDelete.class));
    }


}
