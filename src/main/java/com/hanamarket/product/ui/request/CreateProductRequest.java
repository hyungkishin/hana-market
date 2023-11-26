package com.hanamarket.product.ui.request;

import com.hanamarket.product.domain.GoodsJpaEntity;
import com.hanamarket.product.domain.GoodsName;
import com.hanamarket.product.domain.GoodsStatus;
import lombok.Getter;

@Getter
public class CreateProductRequest {

    private String productName;

    private GoodsStatus status;

    private int sellPrice;

    private String description;

    public CreateProductRequest(String productName, GoodsStatus status, int sellPrice, String description) {
        this.productName = productName;
        this.status = status;
        this.sellPrice = sellPrice;
        this.description = description;
    }

    public GoodsJpaEntity toEntity() {
        return GoodsJpaEntity.builder()
                .goodsName(new GoodsName(productName))
                .status(status)
                .sellPrice(sellPrice)
                .description(description)
                .build();
    }

}
