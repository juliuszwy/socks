package com.scoks.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单主纱
 * </p>
 *
 * @author julius
 * @since 2022-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderProduct extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 订单号
     */
    private Long orderId;

    private String itemNum;

    /**
     * 主纱
     */
    private String mainYarn;

    private String liningYarn;

    private String size;

    /**
     * 目标数量
     */
    private Long targetNum;

    /**
     * 完成数量
     */
    private Long completedNum;

    /**
     * 定型数量
     */
    private Long finalizeNum;

    /**
     * 外发生产目标数量
     */
    private Long outTargetNum;

    /**
     * 外发生产完成数量
     */
    private Long outCompletedNum;

    /**
     * 0 正常 1删除
     */
    private Integer state;

    private String other;

    @TableField("`desc`")
    private String desc;

    private Long createTime;

    private Long updateTime;


    @TableField(exist = false)
    private String partnerName;
    @TableField(exist = false)
    private String partnerId;


}
