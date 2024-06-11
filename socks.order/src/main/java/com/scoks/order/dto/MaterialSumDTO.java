package com.scoks.order.dto;

import com.scoks.order.entity.Material;
import lombok.Data;

@Data
public class MaterialSumDTO {
    private Material material;
    private Long sum;
    private Long orderLackSum;
}