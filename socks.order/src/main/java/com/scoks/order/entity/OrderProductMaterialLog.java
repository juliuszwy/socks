package com.scoks.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author julius
 * @since 2022-11-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderProductMaterialLog extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long opmId;
    /**
     * 已经领取
     */
    private BigDecimal getNum;

    private Long creator;

    private Long createTime;

    private Long updateTime;


    @TableField(exist = false)
    private String creatorName;
    @TableField(exist = false)
    private Material material;
    @TableField(exist = false)
    private OrderProduct orderProduct;
}