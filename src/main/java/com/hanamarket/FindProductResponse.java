package com.hanamarket;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

public class FindProductResponse {

    private String productName;

    private int viewCount;

    private ProductStatus status;

    private int sellPrice;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @Builder(access = AccessLevel.PROTECTED)
    public FindProductResponse(String productName,
                               int viewCount,
                               ProductStatus status,
                               int sellPrice,
                               String description,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
        this.productName = productName;
        this.viewCount = viewCount;
        this.status = status;
        this.sellPrice = sellPrice;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static FindProductResponse of(ProductJpaEntity productJpaEntity) {
        return FindProductResponse.builder()
                .productName(productJpaEntity.getProductName())
                .viewCount(productJpaEntity.getViewCount())
                .status(productJpaEntity.getStatus())
                .sellPrice(productJpaEntity.getSellPrice())
                .description(productJpaEntity.getDescription())
                .createdAt(productJpaEntity.getCreatedAt())
                .updatedAt(productJpaEntity.getUpdatedAt())
                .build();
    }

}
