package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.dto.OrderMaterialSumDTO;
import com.scoks.order.entity.OrderProductMaterialLog;
import com.scoks.order.query.OrderMaterialQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface OrderProductMaterialLogMapper extends BaseMapper<OrderProductMaterialLog> {

    List<OrderProductMaterialLog> listOrderProductMaterialLogs(Page<OrderProductMaterialLog> page, @Param("where") OrderMaterialQuery form);

    List<OrderMaterialSumDTO> statisticsStorageMaterialPageList(Page<OrderMaterialSumDTO> page, @Param("where") OrderMaterialQuery form);
}