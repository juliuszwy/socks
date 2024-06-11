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
 * @since 2022-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderProductWork extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    //@NotNull(message = "产品编号是必须的！")
    private Long productId;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 生产者
     */
    private Long worker;

    private Long workTime;
    private Long completed;

    private Long creator;

    private String machine;
    private Integer machineId;

    private Integer state;
    private String other;

    private Long createTime;

    private Long updateTime;

    @TableField(exist = false)
    private String workName;

    @TableField(exist = false)
    private String machineNum;

    @TableField(exist = false)
    private String pinNum;

    @TableField(exist = false)
    private String pinType;
    @TableField(exist = false)
    private Long startTime;
    @TableField(exist = false)
    private Long endTime;


}
