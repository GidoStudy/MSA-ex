package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.entity.OrderProduct;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private String name;
    private List<OrderProduct> productIds;
}
