package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @CacheEvict(cacheNames = "productList", allEntries = true)
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {
        Product product = Product.toEntity(requestDto);
        Product savedProduct = productRepository.save(product);
        return toResponseDto(savedProduct);
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "productList", key = "methodName")
    public List<ProductResponseDto> productList() {
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            log.info("product name : {}", product.getName());
        }
        return productList.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    private ProductResponseDto toResponseDto(Product savedProduct) {
        return new ProductResponseDto(
                savedProduct.getId(), savedProduct.getName(), savedProduct.getSupplyPrice()
        );
    }
}
