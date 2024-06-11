package com.scoks.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scoks.order.entity.OrderProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单主纱 Mapper 接口
 * </p>
 *
 * @author julius
 * @since 2022-02-22
 */
@Mapper
public interface OrderProductMapper extends BaseMapper<OrderProduct> {

    int updateOutTargetNum(@Param("id") Long id, @Param("num") Long num, @Param("updateTime") Long updateTime);

    int updateOutCompletedNum(@Param("id") Long id, @Param("num") Long num, @Param("updateTime") Long updateTime);

    int updateCompletedNum(@Param("id") Long id, @Param("num") Long num, @Param("updateTime") Long updateTime);

    int updateFinalizeNum(@Param("id") Long id, @Param("num") Long num, @Param("updateTime") Long updateTime);

    List<OrderProduct> listProduct(@Param("where") OrderProduct orderProduct);

    List<OrderProduct> listProductGroupOut(@Param("where") OrderProduct orderProduct);

}
