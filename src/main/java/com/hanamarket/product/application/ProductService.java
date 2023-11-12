package com.hanamarket.product.application;

import com.hanamarket.common.exception.MarketRuntimeException;
import com.hanamarket.common.exception.message.ProductErrorCode;
import com.hanamarket.product.domain.ProductJpaEntity;
import com.hanamarket.product.infrastructure.ProductRepository;
import com.hanamarket.product.ui.request.CreateProductRequest;
import com.hanamarket.product.ui.request.ProductSearchCriteria;
import com.hanamarket.product.ui.response.CreateProductResponse;
import com.hanamarket.product.ui.response.FindProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public CreateProductResponse createProduct(final CreateProductRequest request) {
        ProductJpaEntity createProduct = productRepository.save(request.toEntity());
        return CreateProductResponse.of(createProduct.getId());
    }

    public FindProductResponse findByProduct(final Long productId) {
        ProductJpaEntity productJpaEntity = productRepository.findById(productId)
                .orElseThrow(() -> new MarketRuntimeException(ProductErrorCode.NOT_FOUND_PRODUCT, "존재하지 않는 상품입니다"));

        return FindProductResponse.of(productJpaEntity);
    }

    public Page<ProductJpaEntity> searchProducts(ProductSearchCriteria criteria) {
        Page<ProductJpaEntity> productList = productRepository.findByCreatedAtBetweenAndSellPriceBetweenAndStatus(
                criteria.getStartDate(), criteria.getEndDate(),
                criteria.getMinPrice(), criteria.getMaxPrice(),
                criteria.getStatus(), PageRequest.of(criteria.getPage(), criteria.getSize()));

        System.out.println("productList = " + productList);

        return null;
    }

}
