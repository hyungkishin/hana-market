package com.hanamarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("상품 명 테스트")
class ProductNameTest {

    @Test
    void 상품_이름은_null_일_수_없다() {

        // When & Then
        assertThatThrownBy(() -> new ProductName(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

}