package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoks.order.entity.OrderProductOut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author julius
 * @since 2022-04-09
 */
@Mapper
public interface OrderProductOutMapper extends BaseMapper<OrderProductOut> {

    List<OrderProductOut> pageProductOut(Page<OrderProductOut> page, @Param("where") OrderProductOut where);
}
