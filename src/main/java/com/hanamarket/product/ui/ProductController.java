package com.hanamarket.product.ui;

import com.hanamarket.product.application.ProductService;
import com.hanamarket.product.domain.ProductJpaEntity;
import com.hanamarket.product.ui.request.CreateProductRequest;
import com.hanamarket.product.ui.request.ProductSearchCriteria;
import com.hanamarket.product.ui.response.CreateProductResponse;
import com.hanamarket.product.ui.response.FindProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    private CreateProductResponse createProduct(@Valid @RequestBody final CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping("/{productId}")
    private FindProductResponse findByProduct(@PathVariable final Long productId) {
        return productService.findByProduct(productId);
    }

    @GetMapping("/search")
    public Page<ProductJpaEntity> searchProducts(ProductSearchCriteria criteria) {
        return productService.searchProducts(criteria);
    }

}
