package com.sparta.msa_exam.order.entity;

import com.sparta.msa_exam.order.dto.OrderRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Entity
@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> product_ids = new ArrayList<>();

    public static Order toEntity(OrderRequestDto requestDto) {
        return Order.builder()
                .name(requestDto.getName())
                .product_ids(requestDto.getProductList())
                .build();
    }
}
