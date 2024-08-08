package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.controller.ProductClient;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.dto.ProductResponseDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import com.sparta.msa_exam.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Order order = Order.toEntity(requestDto);
        orderRepository.save(order);
        return toResponseDto(order);
    }

    private OrderResponseDto toResponseDto(Order order) {
        return OrderResponseDto.builder()
                .name(order.getName())
                .productIds(order.getProduct_ids())
                .build();
    }

    public void updateOrder(long orderId, long productId) {
        List<ProductResponseDto> productList = productClient.getProduct();
        if (productList.isEmpty()) {
            log.info("productList is Empty");
            throw new IllegalStateException("상품 목록이 없습니다");
        }
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException(orderId + "에 해당하는 주문이 없습니다"));
        if (existsProductId(productId, productList)) {
            List<OrderProduct> productIds = order.getProduct_ids();
            productIds.add(OrderProduct.builder()
                    .order(order)
                    .productId(productId)
                    .build());
            orderRepository.save(order);
        }
    }

    private boolean existsProductId(long productId, List<ProductResponseDto> productList) {
        for (ProductResponseDto productResponseDto : productList) {
            if (productResponseDto.getProductId() != productId) {
                return false;
            }
        }
        return true;
    }


}
