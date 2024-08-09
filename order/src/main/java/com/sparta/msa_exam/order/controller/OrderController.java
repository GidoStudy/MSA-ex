package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.service.OrderService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto requestDto) {
        return orderService.createOrder(requestDto);
    }

    @PutMapping("/{orderId}")
    public void addOrder(@PathVariable long orderId, @RequestParam long product_id) {
        log.info("addOrder 호출");
        orderService.updateOrder(orderId, product_id);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOneOrder(@PathVariable long orderId) {
        return orderService.getOneOrder(orderId);
    }
}
