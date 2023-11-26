package com.hanamarket.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class GoodsName {

    @Column(name = "goods_name", length = 100, columnDefinition = "VARCHAR(255) COMMENT '상품 제목'", nullable = false)
    private String productName;

    public GoodsName(String productName) {
        validate(productName);
        this.productName = productName;
    }

    public GoodsName() {

    }

    private void validate(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException();
        }
    }

    public String getValue() {
        return this.productName;
    }

}
