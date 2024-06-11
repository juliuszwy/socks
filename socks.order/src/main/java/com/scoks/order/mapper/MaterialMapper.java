package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.entity.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 原料 Mapper 接口
 * </p>
 *
 * @author julius
 * @since 2022-10-30
 */
@Mapper
public interface MaterialMapper extends BaseMapper<Material> {
    List<Material> listMaterials(Page<Material> page, @Param("where") Material form);

    Material findMaterialBaseById(Long id);
}
