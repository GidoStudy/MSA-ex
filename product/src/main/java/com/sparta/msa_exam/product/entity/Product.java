package com.sparta.msa_exam.product.entity;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import jakarta.persistence.*;
import jakarta.ws.rs.GET;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private Integer supplyPrice;

    public static Product toEntity(ProductRequestDto requestDto) {
        return Product.builder()
                .name(requestDto.getName())
                .supplyPrice(requestDto.getSupplyPrice())
                .build();
    }
}
