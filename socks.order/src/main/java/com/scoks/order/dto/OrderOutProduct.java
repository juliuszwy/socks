package com.scoks.order.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class OrderOutProduct {
    private Long id;
    private Long productId;
    /**
     * 订单号
     */
    private Long orderId;

    private String itemNum;

    /**
     * 主纱
     */
    private String mainYarn;

    private String liningYarn;

    private String size;

    /**
     * 目标数量
     */
    private Long targetNum;

    /**
     * 完成数量
     */
    private Long completedNum;


    /**
     * 0 正常 1删除
     */
    private Integer state;

    private String other;

    @TableField("`desc`")
    private String desc;

    private Long createTime;

    private Long updateTime;

    private Long partnerId;
    private String partnerName;
}
