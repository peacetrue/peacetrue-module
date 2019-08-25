package com.github.peacetrue.template.modules.demo;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static com.github.peacetrue.template.modules.demo.DemoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

@Mapper
public interface DemoMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(InsertStatementProvider<Demo> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("DemoResult")
    <Id, OperatorId> Demo<Id, OperatorId> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "DemoResult", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifier_id", property = "modifierId"),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP),
    })
    <Id, OperatorId> List<Demo<Id, OperatorId>> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(demo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, demo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Object id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, demo)
                .where((SqlColumn<Object>) id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteInPrimaryKey(Collection<?> id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, demo)
                .where((SqlColumn<Object>) id, isIn(id_ instanceof List ? (List<Object>) id_ : new LinkedList<Object>(id_)))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Demo record) {
        return insert(SqlBuilder.insert(record)
                .into(demo)
                .map(id).toProperty("id")
                .map(code).toProperty("code")
                .map(name).toProperty("name")
                .map(creatorId).toProperty("creatorId")
                .map(createdTime).toProperty("createdTime")
                .map(modifierId).toProperty("modifierId")
                .map(modifiedTime).toProperty("modifiedTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Demo record) {
        return insert(SqlBuilder.insert(record)
                .into(demo)
                .map((SqlColumn<Object>) id).toPropertyWhenPresent("id", record::getId)
                .map(code).toPropertyWhenPresent("code", record::getCode)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map((SqlColumn<Object>) creatorId).toPropertyWhenPresent("creatorId", record::getCreatorId)
                .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
                .map((SqlColumn<Object>) modifierId).toPropertyWhenPresent("modifierId", record::getModifierId)
                .map(modifiedTime).toPropertyWhenPresent("modifiedTime", record::getModifiedTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default <Id, OperatorId> QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Demo<Id, OperatorId>>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::<Id, OperatorId>selectMany, id, code, name, creatorId, createdTime, modifierId, modifiedTime)
                .from(demo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default <Id, OperatorId> QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Demo<Id, OperatorId>>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::<Id, OperatorId>selectMany, id, code, name, creatorId, createdTime, modifierId, modifiedTime)
                .from(demo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Demo selectByPrimaryKey(Object id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, code, name, creatorId, createdTime, modifierId, modifiedTime)
                .from(demo)
                .where((SqlColumn<Object>) id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Demo record) {
        return UpdateDSL.updateWithMapper(this::update, demo)
                .set((SqlColumn<Object>) id).equalTo(record::getId)
                .set(code).equalTo(record::getCode)
                .set(name).equalTo(record::getName)
                .set((SqlColumn<Object>) creatorId).equalTo(record::getCreatorId)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set((SqlColumn<Object>) modifierId).equalTo(record::getModifierId)
                .set(modifiedTime).equalTo(record::getModifiedTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Demo record) {
        return UpdateDSL.updateWithMapper(this::update, demo)
                .set((SqlColumn<Object>) id).equalToWhenPresent(record::getId)
                .set(code).equalToWhenPresent(record::getCode)
                .set(name).equalToWhenPresent(record::getName)
                .set((SqlColumn<Object>) creatorId).equalToWhenPresent(record::getCreatorId)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set((SqlColumn<Object>) modifierId).equalToWhenPresent(record::getModifierId)
                .set(modifiedTime).equalToWhenPresent(record::getModifiedTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Demo record) {
        return UpdateDSL.updateWithMapper(this::update, demo)
                .set(code).equalTo(record::getCode)
                .set(name).equalTo(record::getName)
                .set((SqlColumn<Object>) creatorId).equalTo(record::getCreatorId)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set((SqlColumn<Object>) modifierId).equalTo(record::getModifierId)
                .set(modifiedTime).equalTo(record::getModifiedTime)
                .where((SqlColumn<Object>) id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Demo record) {
        return UpdateDSL.updateWithMapper(this::update, demo)
                .set(code).equalToWhenPresent(record::getCode)
                .set(name).equalToWhenPresent(record::getName)
                .set((SqlColumn<Object>) creatorId).equalToWhenPresent(record::getCreatorId)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set((SqlColumn<Object>) modifierId).equalToWhenPresent(record::getModifierId)
                .set(modifiedTime).equalToWhenPresent(record::getModifiedTime)
                .where((SqlColumn<Object>) id, isEqualTo(record::getId))
                .build()
                .execute();
    }


    //append
    @SuppressWarnings("unchecked")
    default <Id, OperatorId> List<Demo<Id, OperatorId>> selectById(Collection<Id> ids) {
        return this.<Id, OperatorId>selectByExample().where((SqlColumn<Id>) demo.id, SqlBuilder.isIn(new ArrayList<>(ids))).build().execute();
    }

}