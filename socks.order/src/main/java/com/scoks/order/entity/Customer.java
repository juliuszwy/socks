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
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 国家
     */
    private Integer country;

    /**
     * 创建人
     */
    private Long creator;

    private Long salesman;

    private Integer state;

    private Long createTime;

    private Long updateTime;

    @TableField(exist = false)
    private String salesmanName;
    @TableField(exist = false)
    private String countryName;


}
