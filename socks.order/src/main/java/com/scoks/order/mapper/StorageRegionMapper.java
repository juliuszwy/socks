package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.entity.StorageRegion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 仓库区域原料统计 Mapper 接口
 * </p>
 *
 * @author julius
 * @since 2022-10-30
 */
@Mapper
public interface StorageRegionMapper extends BaseMapper<StorageRegion> {

    List<StorageRegion> findStorageRegionPageList(Page<StorageRegion> page, StorageRegion where);
}