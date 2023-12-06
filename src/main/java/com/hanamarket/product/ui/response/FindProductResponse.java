package com.hanamarket.product.ui.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanamarket.product.domain.Goods;
import com.hanamarket.product.domain.GoodsStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindProductResponse {

    private String productName;

    private int viewCount;

    private GoodsStatus status;

    private int sellPrice;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;


    @Builder
    public FindProductResponse(String productName,
                               int viewCount,
                               GoodsStatus status,
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

    public static FindProductResponse of(Goods goods) {
        return FindProductResponse.builder()
                .productName(goods.getGoodsName())
                .viewCount(goods.getViewCount())
                .status(goods.getStatus())
                .sellPrice(goods.getSellPrice())
                .description(goods.getDescription())
                .createdAt(goods.getCreatedAt())
                .updatedAt(goods.getUpdatedAt())
                .build();
    }

}
