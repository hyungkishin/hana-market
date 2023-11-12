package com.hanamarket.product.ui.response;

import lombok.Getter;

@Getter
public class CreateProductResponse {

    private Long productId;

    public CreateProductResponse(Long productId) {
        this.productId = productId;
    }

    public static CreateProductResponse of(Long productId) {
        return new CreateProductResponse(productId);
    }

}
