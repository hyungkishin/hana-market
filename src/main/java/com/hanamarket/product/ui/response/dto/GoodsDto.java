package com.hanamarket.product.ui.response.dto;

import com.hanamarket.product.domain.GoodsJpaEntity;
import com.hanamarket.product.domain.GoodsStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

// 씁 .... 얘를 어디다 둔다....
@Getter
@Builder
public class GoodsDto {

    private Long id;

    private String goodsName;

    private int viewCount;

    private GoodsStatus status;

    private int sellPrice;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static GoodsDto fromEntity(GoodsJpaEntity goodsJpaEntity) {
        return GoodsDto.builder()
                .id(goodsJpaEntity.getId())
                .goodsName(goodsJpaEntity.getGoodsName())
                .viewCount(goodsJpaEntity.getViewCount())
                .status(goodsJpaEntity.getStatus())
                .sellPrice(goodsJpaEntity.getSellPrice())
                .description(goodsJpaEntity.getDescription())
                .createdAt(goodsJpaEntity.getCreatedAt())
                .updatedAt(goodsJpaEntity.getUpdatedAt())
                .build();
    }

}