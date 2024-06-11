package com.scoks.order.dto;

import lombok.Data;

@Data
public class OrderQuery {
    private Long id;
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 客户id
     */
    private Long customerId;

    private String customerName;

    /**
     * 业务员id
     */
    private Long salesmanId;

    private String salesmanName;

    /**
     * 加急 ： 0 未加急 1加急
     */
    private Integer urgent;

    // 1 将要完成订单
    private Integer state;

    private String itemNum;

    private Integer finalizeState;

    private Integer produceState;

    //存在外发的订单
    private Boolean out;
    //即将完成
    private Integer wbd;

}
