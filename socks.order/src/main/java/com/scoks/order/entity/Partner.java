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
public class Partner extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 合作伙伴姓名
     */
    private String name;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 关联登录系统账号id
     */
    private Long loginId;

    private Integer state;

    private Long createTime;

    private Long updateTime;


}
