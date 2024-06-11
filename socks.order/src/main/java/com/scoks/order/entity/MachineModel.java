package com.scoks.order.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 机型
 * </p>
 *
 * @author julius
 * @since 2022-10-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MachineModel extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 针数
     */
    private String pinNum;

    private String pinType;

    private Long createTime;

    private Long updateTime;


}
