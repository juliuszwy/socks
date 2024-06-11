package com.scoks.order.query;

import lombok.Data;

/**
 * @author zhuweiyi
 * @description:
 * @since 2023/09/19
 */
@Data
public class OrderMaterialQuery {
    private Long orderId;
    private Long productId;
    private Integer category;
    private String colour;
}