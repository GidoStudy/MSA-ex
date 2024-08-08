package com.sparta.msa_exam.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private long productId;
    private String name;
    private Integer supplyPrice;
}
