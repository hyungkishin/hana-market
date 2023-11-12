package com.hanamarket;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
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

}
