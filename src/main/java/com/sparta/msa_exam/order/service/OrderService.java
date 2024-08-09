package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.controller.ProductClient;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.dto.ProductResponseDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import com.sparta.msa_exam.order.repository.OrderProductRepository;
import com.sparta.msa_exam.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final OrderProductRepository orderProductRepository;

    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Order order = Order.toEntity(requestDto);
        orderRepository.save(order);
        return toResponseDto(order);
    }

    private OrderResponseDto toResponseDto(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
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
            log.info("if문 안으로");
            List<OrderProduct> productIds = order.getProduct_ids();
            OrderProduct orderProduct = OrderProduct.builder()
                    .order(order)
                    .productId(productId)
                    .build();
            productIds.add(orderProduct);
            log.info("orderProduct.order, productId : {} {}", orderProduct.getOrder().getId(), orderProduct.getProductId());
            log.info("save 호출");
            orderProductRepository.save(orderProduct);
            orderRepository.save(order);
        }
    }

    private boolean existsProductId(long productId, List<ProductResponseDto> productList) {
        for (ProductResponseDto productResponseDto : productList) {
            if (productResponseDto.getProductId() == productId) {
                return true;
            }
        }
        return false;
    }

    @Cacheable(cacheNames = "order" , key = "args[0]")
    public OrderResponseDto getOneOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException("해당 주문이 존재하지 않습니다"));
        return toResponseDto(order);
    }
}
