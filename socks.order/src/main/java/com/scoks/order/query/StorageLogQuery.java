package com.scoks.order.query;

import lombok.Data;

/**
 * @author zhuweiyi
 * @description:
 * @since 2023/09/19
 */
@Data
public class StorageLogQuery {
    private String tray;
    private Integer category;
    private String name;
    private String colour;
    private Long startTime;
    private Long endTime;
}