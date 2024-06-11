package com.scoks.order.entity;

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
public class OrderProductOutWork extends BaseEntity {

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
     * 外发产品id
     */
    private Long productOutId;

    private Long workTime;

    /**
     * 完成数量
     */
    private Long completed;

    private Long creator;

    /**
     * 0 正常 1删除
     */
    private Integer state;

    private String other;

    private Long createTime;

    private Long updateTime;


}
