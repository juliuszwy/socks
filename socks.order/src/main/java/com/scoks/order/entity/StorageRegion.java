package com.scoks.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 仓库区域原料统计
 * </p>
 *
 * @author julius
 * @since 2022-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class StorageRegion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 区域
     */
    private String region;

    /**
     * 托盘
     */
    private String tray;

    /**
     * 操作人
     */
    private Long operator;
    /**
     * 备注
     */
    private String remarks;

    private Long createTime;

    private Long updateTime;

    @TableField(exist = false)
    private String operatorName;

    @TableField(exist = false)
    private Long materialTypeNum;

}
