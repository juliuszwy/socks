package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.entity.StorageLog;
import com.scoks.order.query.StorageLogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 仓库 出入库记录 Mapper 接口
 * </p>
 *
 * @author julius
 * @since 2022-10-30
 */
@Mapper
public interface StorageLogMapper extends BaseMapper<StorageLog> {
    List<StorageLog> listStorageLog(Page<StorageLog> page, @Param("where") StorageLogQuery where);
}