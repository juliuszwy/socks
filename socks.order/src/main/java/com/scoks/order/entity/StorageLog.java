package com.scoks.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 仓库 出入库记录
 * </p>
 *
 * @author julius
 * @since 2022-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class StorageLog extends BaseEntity {
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
     * 厂家
     */
    private String manufactor;

    /**
     * 原料数量
     */
    private BigDecimal num;

    /**
     * 1：新采购原料 2订单退回原料 3打样出库 4 销毁出库
     */
    private Integer type;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 录入人
     */
    private Long creator;

    private Long createTime;

    private Long updateTime;

    @TableField(exist = false)
    private Material material;
    @TableField(exist = false)
    private String region;
    @TableField(exist = false)
    private String creatorName;

}
