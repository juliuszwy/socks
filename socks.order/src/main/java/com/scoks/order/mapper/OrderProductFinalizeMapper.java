package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.entity.OrderProductFinalize;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author julius
 * @since 2022-03-17
 */
@Mapper
public interface OrderProductFinalizeMapper extends BaseMapper<OrderProductFinalize> {
    List<OrderProductFinalize> listOrderProductFinalize(Page<OrderProductFinalize> page, @Param("where") OrderProductFinalize where);

}
