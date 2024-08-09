package com.sparta.msa_exam.order.repository;

import com.sparta.msa_exam.order.entity.Order;

public interface OrderRepositoryCustom {

    public void updateOrder(Order order, long productId);
}
