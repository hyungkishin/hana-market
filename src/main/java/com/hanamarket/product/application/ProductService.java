package com.hanamarket.product.application;

import com.hanamarket.product.domain.ProductJpaEntity;
import com.hanamarket.product.infrastructure.ProductRepository;
import com.hanamarket.product.ui.request.CreateProductRequest;
import com.hanamarket.product.ui.response.CreateProductResponse;
import com.hanamarket.product.ui.response.FindProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public CreateProductResponse createProduct(final CreateProductRequest request) {
        ProductJpaEntity createProduct = productRepository.save(request.toEntity());
        return CreateProductResponse.of(createProduct.getId());
    }

    public FindProductResponse findByProduct(final Long productId) {
        ProductJpaEntity productJpaEntity = productRepository.findById(productId)
                .orElseThrow(NoSuchElementException::new);

        return FindProductResponse.of(productJpaEntity);
    }

}
