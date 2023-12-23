package com.hanamarket.product.ui.response.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanamarket.product.domain.Goods;
import com.hanamarket.product.domain.GoodsStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GoodsDto {

    private Long id;

    private String goodsName;

    private int viewCount;

    private GoodsStatus goodsStatus;

    private int sellPrice;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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