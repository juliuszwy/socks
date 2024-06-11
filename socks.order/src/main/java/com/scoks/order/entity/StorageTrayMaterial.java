package com.scoks.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 仓库区域托盘原料统计
 * </p>
 *
 * @author julius
 * @since 2023-9-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class StorageTrayMaterial extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 托盘编号
     */
    private String tray;
    /**
     * 原料id
     */
    private Long materialId;

    /**
     * 原料总量
     */
    private BigDecimal count;

    /**
     * 备注
     */
    private String remarks;

    private Long createTime;

    private Long updateTime;

    @TableField(exist = false)
    private String region;
    @TableField(exist = false)
    private Material material;


}