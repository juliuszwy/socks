package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scoks.order.entity.OrderStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单产品 Mapper 接口
 * </p>
 *
 * @author julius
 * @since 2022-04-09
 */
@Mapper
public interface OrderStatusMapper extends BaseMapper<OrderStatus> {

    int updateOutTargetNum(@Param("id") Long id, @Param("num") Long num, @Param("updateTime") Long updateTime);

    int updateOutCompletedNum(@Param("id") Long id, @Param("num") Long num, @Param("updateTime") Long updateTime);

    int updateCompletedNum(@Param("id") Long id, @Param("num") Long num, @Param("updateTime") Long updateTime);

    int updateFinalizeNum(@Param("id") Long id, @Param("num") Long num, @Param("updateTime") Long updateTime);

    int updateTargetNum(@Param("id") Long id, @Param("num") Long num, @Param("updateTime") Long updateTime);
}
