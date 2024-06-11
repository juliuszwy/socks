package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.dto.MaterialSumDTO;
import com.scoks.order.entity.StorageTrayMaterial;
import com.scoks.order.query.MaterialQuery;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
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
public interface StorageTrayMaterialMapper extends BaseMapper<StorageTrayMaterial> {

    List<StorageTrayMaterial> storageTrayMaterials(Page<StorageTrayMaterial> page, MaterialQuery where);

    int updateNum(Long stmId, BigDecimal num);

    List<MaterialSumDTO> statisticsStorageMaterialPageList(Page<MaterialSumDTO> page, MaterialQuery where);
}