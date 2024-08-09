package com.sparta.msa_exam.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto implements Serializable {

    private long productId;
    private String name;
    private Integer supplyPrice;
}
