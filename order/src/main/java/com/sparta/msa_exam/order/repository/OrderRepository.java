package com.sparta.msa_exam.order.repository;

import com.sparta.msa_exam.order.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
