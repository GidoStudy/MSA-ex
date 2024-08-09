package com.sparta.msa_exam.order.repository;

import com.sparta.msa_exam.order.entity.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final EntityManager em;

    @Override
    public void updateOrder(Order order, long productId) {
    }
}
