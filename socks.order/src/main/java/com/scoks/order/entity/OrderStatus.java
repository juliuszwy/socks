package com.scoks.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单产品
 * </p>
 *
 * @author julius
 * @since 2022-04-09
 */
@Data
@Accessors(chain = true)
public class OrderStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @TableId(type = IdType.INPUT)
    private Long orderId;

    /**
     * 目标数量
     */
    private Long targetNum;

    /**
     * 完成数量
     */
    private Long completedNum;

    /**
     * 外发生产目标数量
     */
    private Long outTargetNum;

    /**
     * 外发生产完成数量
     */
    private Long outCompletedNum;

    /**
     * 定型数量
     */
    private Long finalizeNum;

    private Integer finalizeState;

    private Integer produceState;

    private Long createTime;

    private Long updateTime;


}
