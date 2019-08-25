package com.github.peacetrue.template.modules.demo;

import com.github.pagehelper.PageHelper;
import com.github.peacetrue.core.Range;
import com.github.peacetrue.mybatis.dynamic.MybatisDynamicUtils;
import com.github.peacetrue.pagehelper.PageHelperUtils;
import com.github.peacetrue.spring.util.BeanUtils;
import com.github.peacetrue.util.EntityNotFoundException;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.peacetrue.template.modules.demo.DemoDynamicSqlSupport.*;

/**
 * @author xiayx
 */
public class DemoServiceImpl implements DemoService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public <Id, OperatorId> DemoVO<Id, OperatorId> add(DemoAdd<OperatorId> params) {
        logger.info("新增信息[{}]", params);
        Demo<Id, OperatorId> demo = new Demo<>();
        BeanUtils.copyProperties(params, demo);
        demo.setCreatorId(params.getOperatorId());
        demo.setCreatedTime(new Date());
        demo.setModifierId(params.getOperatorId());
        demo.setModifiedTime(demo.getCreatedTime());
        int count = demoMapper.insertSelective(demo);
        logger.debug("共影响[{}]条记录", count);
        return to(demo);
    }

    private <Id, OperatorId> DemoVO<Id, OperatorId> to(Demo<Id, OperatorId> demo) {
        DemoVO<Id, OperatorId> vo = new DemoVO<>();
        BeanUtils.copyProperties(demo, vo);
        return vo;
    }

    @Override
    public <Id, OperatorId> Page<DemoVO<Id, OperatorId>> query(@Nullable DemoQuery params, Pageable pageable) {
        logger.info("分页查询信息[{}]", params);
        if (params == null) params = new DemoQuery();
        if (params.getCreatedTime() == null) params.setCreatedTime(new Range.Date());
        if (pageable == null) pageable = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "createdTime"));
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        List<Demo<Id, OperatorId>> entities = demoMapper.<Id, OperatorId>selectByExample()
                .where(code, SqlBuilder.isLikeWhenPresent(MybatisDynamicUtils.likeValue(params.getCode())))
                .and(name, SqlBuilder.isEqualToWhenPresent(params.getName()))
                .and(createdTime, SqlBuilder.isBetweenWhenPresent(params.getCreatedTime().getLowerBound()).and(MybatisDynamicUtils.endDateValue(params.getCreatedTime().getUpperBound())))
                .orderBy(MybatisDynamicUtils.orders(demo, pageable.getSort()))
                .build().execute();
        logger.debug("共取得'{}'条记录", entities.size());
        if (entities.isEmpty()) return new PageImpl<>(Collections.emptyList());

        List<DemoVO<Id, OperatorId>> vos = entities.stream().map(this::<Id, OperatorId>to).collect(Collectors.toList());
        return new PageImpl<>(vos, pageable, PageHelperUtils.getTotal(entities));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Id, OperatorId> DemoVO<Id, OperatorId> get(DemoGet<Id, OperatorId> params) {
        logger.info("获取符合条件[{}]的信息", params);
        return demoMapper.<Id, OperatorId>selectByExample()
                .where((SqlColumn<Object>) id, SqlBuilder.isEqualTo(params.getId()))
                .build().execute().stream()
                .reduce((l, r) -> r)
                .map(this::to)
                .orElseThrow(() -> new EntityNotFoundException(Demo.class, "id", params.getId()));
    }

    @Override
    public <Id, OperatorId> int modify(DemoModify<Id, OperatorId> params) {
        logger.info("修改信息[{}]", params);
        Demo<Id, OperatorId> demo = new Demo<>();
        BeanUtils.copyProperties(params, demo);
        demo.setModifierId(params.getOperatorId());
        demo.setModifiedTime(new Date());
        int count = demoMapper.updateByPrimaryKeySelective(demo);
        logger.debug("共影响[{}]条记录", count);
        return count;
    }

    @Override
    public <T, OperatorId> int delete(DemoDelete<T, OperatorId> params) {
        logger.info("删除信息[{}]", params);
        int count = params.getId().length == 1
                ? demoMapper.deleteByPrimaryKey(params.getId()[0])
                : demoMapper.deleteInPrimaryKey(Arrays.asList(params.getId()));
        logger.debug("共影响[{}]条记录", count);
        return count;
    }
}
