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
 * @since 2022-10-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MachineInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 机器编号
     */
    private String machineNum;

    /**
     * 机器型号
     */
    private String model;

    /**
     * 备注
     */
    private String remarks;

    private Long creator;

    private Long createTime;

    private Long updateTime;


    @TableField(exist = false)
    private MachineModel machineModel;


    @TableField(exist = false)
    private String pinNum;

    @TableField(exist = false)
    private String pinType;

}
