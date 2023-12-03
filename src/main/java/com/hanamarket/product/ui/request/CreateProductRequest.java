package com.hanamarket.product.ui.request;

import com.hanamarket.product.domain.GoodsJpaEntity;
import com.hanamarket.product.domain.GoodsName;
import com.hanamarket.product.domain.GoodsStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateProductRequest {

    private String goodsName;

    private GoodsStatus status;

    private int sellPrice;

    private String description;

    public GoodsJpaEntity toEntity() {
        return GoodsJpaEntity.builder()
                .goodsName(new GoodsName(goodsName))
                .status(status)
                .sellPrice(sellPrice)
                .description(description)
                .build();
    }

}
