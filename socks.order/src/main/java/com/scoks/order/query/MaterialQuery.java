package com.scoks.order.query;

import lombok.Data;

/**
 * @author zhuweiyi
 * @description:
 * @since 2023/09/19
 */
@Data
public class MaterialQuery {
    private String region;
    private String tray;
    private Integer category;
    private String name;
    private String colour;
}
