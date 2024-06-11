package com.scoks.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author julius
 * @since 2022-02-22
 */
@Data
@Accessors(chain = true)
@TableName("`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 业务员id
     */
    private Integer salesmanId;

    private Long deliveryDate;

    /**
     * 加急 ： 0 未加急 1加急
     */
    private Integer urgent;

    /**
     * 设备
     */
    private String device;

    private String sewingHead;

    /**
     * 用料
     */
    private String materials;

    @TableField("`desc`")
    private String desc;

    private String ext1;

    private String ext2;

    private Long creator;

    private Integer state;

    private String other;
    /**
     * 加急时间
     */
    private Long urgentTime;

    private Long createTime;

    private Long updateTime;

    @TableField(exist = false)
    private OrderStatus orderStatus;

    @TableField(exist = false)
    private List<OrderProduct> products;

    @TableField(exist = false)
    private String customerName;
    @TableField(exist = false)
    private String salesmanName;
    @TableField(exist = false)
    private String itemNum;

    @TableField(exist = false)
    private Integer position;

}
