package com.scoks.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author julius
 * @since 2022-04-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderProductOut extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主纱对应的id
     */
    private Long productId;

    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 外发厂商id
     */
    private Long partnerId;

    /**
     * 合作厂商id
     */
    private Long targetNum;

    private Long completedNum;

    private Long creator;

    /**
     * 状态 0 正常 1删除
     */
    private Integer state;

    private String other;

    private Long createTime;

    private Long updateTime;

    @TableField(exist = false)
    private String partnerName;


}
