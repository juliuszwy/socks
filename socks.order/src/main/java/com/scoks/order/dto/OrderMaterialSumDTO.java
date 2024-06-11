package com.scoks.order.dto;

import com.scoks.order.entity.Material;
import lombok.Data;

@Data
public class OrderMaterialSumDTO {
    private Material material;
    private Long targetNum;
    private Long getNum;
    private Long storeNum;
}