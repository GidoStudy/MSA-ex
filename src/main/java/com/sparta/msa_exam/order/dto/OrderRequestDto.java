package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.entity.OrderProduct;
import jdk.jfr.SettingDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrderRequestDto {
    private String name;
    private List<OrderProduct> productList = new ArrayList<>();
}
