package com.hanamarket.product.ui.request;

import com.hanamarket.product.domain.Goods;
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

    public Goods toEntity() {
        return Goods.builder()
                .goodsName(goodsName)
                .status(status)
                .sellPrice(sellPrice)
                .description(description)
                .build();
    }

}
