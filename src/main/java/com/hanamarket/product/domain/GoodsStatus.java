package com.hanamarket.product.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.hanamarket.common.converter.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GoodsStatus implements BaseEnum {
    NEW("C", "신규"),
    RESERVED("R", "예약중"),
    SOLDOUT("S", "판매 완료");

    @JsonValue
    private final String code;
    private final String description;

}