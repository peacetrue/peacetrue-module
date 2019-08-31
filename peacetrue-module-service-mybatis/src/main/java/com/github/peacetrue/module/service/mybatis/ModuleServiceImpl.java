package com.github.peacetrue.module.service.mybatis;

import com.github.peacetrue.metadata.service.Module;
import com.github.peacetrue.metadata.service.PropertyNameValue;
import com.github.peacetrue.metadata.service.RecordInfo;
import com.github.peacetrue.module.service.ModifyOptions;
import com.github.peacetrue.module.service.ModuleService;
import com.github.peacetrue.mybatis.mapper.CommonMapper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * @author xiayx
 */
@SuppressWarnings("unchecked")
public class ModuleServiceImpl implements ModuleService {

    public static final String ID_PROPERTY = "id";
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Set<String> ignoredPropertiesWhenModify = Collections.emptySet();

    public ModuleServiceImpl() {
    }

    public ModuleServiceImpl(Set<String> ignoredPropertiesWhenModify) {
        this.ignoredPropertiesWhenModify = Objects.requireNonNull(ignoredPropertiesWhenModify);
    }

    //    @Autowired
//    private ApplicationEventPublisher eventPublisher;

//    @Override
//    public <VO> VO add(Object params) {
//        logger.info("新增记录[{}]", params);
//        ModuleContext moduleContext = moduleContextResolver.resolveModuleContext(params.getClass());
//        Object entity = BeanUtils.map(params, moduleContext.getEntityClass());
//        logger.debug("取得实体对象[{}]", entity);
//        eventPublisher.publishEvent(new PayloadApplicationEvent<>(entity, params));
//        Method insertSelective = ReflectionUtils.findMethod(moduleContext.getMapper().getClass(), "insertSelective", moduleContext.getEntityClass());
//        logger.debug("取得 insertSelective 方法[{}]", insertSelective);
//        Object count = ReflectionUtils.invokeMethod(insertSelective, moduleContext.getMapper(), entity);
//        logger.debug("共新增[{}]条记录", count);
//        Object vo = BeanUtils.map(entity, moduleContext.getVoClass());
//        eventPublisher.publishEvent(new PayloadApplicationEvent<>(entity, vo));
//        return (VO) vo;
//    }

//    @Override
//    public <VO, Params extends ModuleNameCapable & IdCapable> VO getRequiredById(Params params) {
//        logger.info("获取记录[{}]的详情", params);
//        ModuleContext moduleContext = moduleContextResolver.resolveModuleContext(params.getModuleName());
//        Method selectByExample = ReflectionUtils.findMethod(moduleContext.getMapper().getClass(), "selectByExample");
//        logger.debug("取得 selectByExample 方法[{}]", selectByExample);
//        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Object>>> dsl =
//                (QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Object>>>) ReflectionUtils.invokeMethod(selectByExample, moduleContext.getMapper());
//        return (VO) dsl
//                .where((SqlColumn<Object>) ModuleContextResolverImpl.getFieldValue(moduleContext.getSqlTable(), ID_PROPERTY), SqlBuilder.isEqualTo(params.getId()))
//                .build().execute().stream()
//                .reduce((l, r) -> r)
//                .map(entity -> BeanUtils.map(entity, moduleContext.getVoClass()))
//                .orElseThrow(() -> new EntityNotFoundException(moduleContext.getEntityClass(), ID_PROPERTY, params.getId()));
//    }

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private com.github.peacetrue.metadata.service.ModuleService moduleService;

    @Override
    @Transactional(readOnly = true)
    public boolean exists(RecordInfo params) {
        logger.info("判断记录[{}]是否存在", params);

        SqlTable sqlTable = getSqlTable(params);
        QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>>.QueryExpressionWhereBuilder where = commonMapper.countByExample(sqlTable).where();
        for (PropertyNameValue nameValue : params.getPropertyNameValues()) {
            if (nameValue.getPropertyValue() == null) continue;
            logger.debug("添加条件[{}]", nameValue);
            SqlColumn<Object> sqlColumn = (SqlColumn<Object>) getFiledValue(sqlTable, nameValue.getPropertyName());
            where.and(sqlColumn, SqlBuilder.isEqualTo(nameValue.getPropertyValue()));
        }
        Long count = where.build().execute();
        logger.debug("共取得[{}]条记录", count);
        return count > 0;
    }

    private SqlTable getSqlTable(RecordInfo params) {
        Module module = moduleService.getModule(params.getModuleName());
        logger.debug("取得模型信息[{}]", module);

        Class moduleClass = module.getType().getSuperclass();
        if (moduleClass.equals(Object.class)) moduleClass = module.getType();
        String dynamicSqlSupportClassName = moduleClass.getName() + "DynamicSqlSupport";
        Class<?> dynamicSqlSupportClass = forClass(dynamicSqlSupportClassName);
        SqlTable sqlTable = (SqlTable) getFiledValue(dynamicSqlSupportClass, StringUtils.uncapitalize(params.getModuleName()));
        logger.debug("取得表信息[{}]", sqlTable);
        return sqlTable;
    }

    private static Class<?> forClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(String.format("class[%s] can't found", className));
        }
    }

    private static Object getFiledValue(Object object, String name) {
        Field propertyNameField = ReflectionUtils.findField(object instanceof Class ? (Class) object : object.getClass(), name);
        return ReflectionUtils.getField(propertyNameField, object);
    }

    @Override
    @Transactional
    public int modify(RecordInfo params, ModifyOptions options) {
        logger.info("使用选项[{}]修改记录[{}]", options, params);

        Set<String> alwaysIgnoredProperties = options.getIgnoredProperties();
        if (alwaysIgnoredProperties == null) alwaysIgnoredProperties = ignoredPropertiesWhenModify;

        SqlTable sqlTable = getSqlTable(params);
        UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> dsl = UpdateDSL.updateWithMapper(provide -> commonMapper.update(provide), sqlTable);
        for (PropertyNameValue nameValue : params.getPropertyNameValues()) {
            String propertyName = nameValue.getPropertyName();
            if (alwaysIgnoredProperties.contains(propertyName)) {
                logger.debug("属性[{}]被配置为忽略", propertyName);
                continue;
            }

            Object propertyValue = nameValue.getPropertyValue();
            SqlColumn<Object> sqlColumn = (SqlColumn<Object>) getFiledValue(sqlTable, nameValue.getPropertyName());
            if (propertyValue == null) {
                if (options.getNotIgnoredNullProperties().contains(propertyName)) {
                    logger.debug("设置属性[{}=<null>]", propertyName);
                    dsl.set(sqlColumn).equalToNull();
                }
            } else {
                logger.debug("设置属性[{}={}]", propertyName, propertyValue);
                dsl.set(sqlColumn).equalTo(propertyValue);
            }
        }

        Object idValue = params.getPropertyNameValues().stream()
                .filter(nameValue -> nameValue.getPropertyName().equals(ID_PROPERTY))
                .findAny().map(PropertyNameValue::getPropertyValue).orElse(null);
        return dsl.where((SqlColumn<Object>) getFiledValue(sqlTable, ID_PROPERTY), SqlBuilder.isEqualTo(idValue)).build().execute();
    }


//    @Override
//    public <Params extends ModuleNameCapable & IdCapable & OperatorCapable> int deleteById(Params params) {
//        logger.info("删除记录[{}]", params);
//        ModuleContext moduleContext = moduleContextResolver.resolveModuleContext(params.getModuleName());
//        Method deleteByExample = ReflectionUtils.findMethod(moduleContext.getMapper().getClass(), "deleteByExample");
//        DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> dsl =
//                (DeleteDSL<MyBatis3DeleteModelAdapter<Integer>>) ReflectionUtils.invokeMethod(deleteByExample, moduleContext.getMapper());
//        SqlColumn<Object> id = (SqlColumn<Object>) ModuleContextResolverImpl.getFieldValue(moduleContext.getSqlTable(), ID_PROPERTY);
//        Integer count = dsl.where(id, SqlBuilder.isEqualTo(params.getId())).build().execute();
//        logger.debug("共删除[{}]条记录", count);
//        return count;
//    }
}
