package com.hanamarket.product.ui.request;

import com.hanamarket.product.domain.Goods;
import com.hanamarket.product.domain.GoodsStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GoodsRequest {

    @NotNull(message = "상품 명을 확인해 주세요")
    private String goodsName;

    @NotNull(message = "판매 할 상품의 상태를 확인해 주세요 ex ( NEW, RESERVED, SOLDOUT) ")
    private GoodsStatus status;

    @NotNull
    private int sellPrice;

    @NotNull
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
