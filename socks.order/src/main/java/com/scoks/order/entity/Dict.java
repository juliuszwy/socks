package com.scoks.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author julius
 * @since 2022-02-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("`dict`")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long parentId;

    @TableField("`key`")
    private String key;


    private String value;

    /**
     * 分组
     */
    @TableField("`group`")
    private String group;

    private Integer sort;

    private Integer state;

    private Long createTime;

    private Long updateTime;

    @TableField(exist = false)
    private Object obj;


}
