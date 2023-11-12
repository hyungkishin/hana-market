package com.hanamarket;

import lombok.Getter;

@Getter
public class CreateProductRequest {

    private String productName;

    private ProductStatus status;

    private int sellPrice;

    private String description;

    public CreateProductRequest(String productName, ProductStatus status, int sellPrice, String description) {
        this.productName = productName;
        this.status = status;
        this.sellPrice = sellPrice;
        this.description = description;
    }

    public ProductJpaEntity toEntity() {
        return ProductJpaEntity.builder()
                .productName(new ProductName(productName))
                .status(status)
                .sellPrice(sellPrice)
                .description(description)
                .build();
    }

}
