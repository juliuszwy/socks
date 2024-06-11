package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.entity.OrderProductMaterial;
import com.scoks.order.query.OrderMaterialQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author julius
 * @since 2022-11-08
 */
@Mapper
public interface OrderProductMaterialMapper extends BaseMapper<OrderProductMaterial> {

    List<OrderProductMaterial> listOrderProductMaterial(Page<OrderProductMaterial> page, @Param("where") OrderMaterialQuery form);

    void updateGetNum(Long id, BigDecimal getNum);

    void updateTargetNum(Long id, Long targetNum);
}