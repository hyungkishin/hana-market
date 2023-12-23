package com.hanamarket.product.ui.response.dto;

import com.hanamarket.product.domain.Goods;
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

    private GoodsStatus goodsStatus;

    private int sellPrice;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static GoodsDto of(Goods goods) {
        return GoodsDto.builder()
                .id(goods.getGoodsId())
                .goodsName(goods.getGoodsName())
                .viewCount(goods.getViewCount())
                .goodsStatus(goods.getStatus())
                .sellPrice(goods.getSellPrice())
                .description(goods.getDescription())
                .createdAt(goods.getCreatedAt())
                .updatedAt(goods.getUpdatedAt())
                .build();
    }

}