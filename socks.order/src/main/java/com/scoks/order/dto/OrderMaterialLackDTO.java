package com.scoks.order.dto;

import com.scoks.order.entity.Material;
import lombok.Data;

@Data
public class OrderMaterialLackDTO {
    private Long orderId;
    private Material material;
    private Long lackNum;
}