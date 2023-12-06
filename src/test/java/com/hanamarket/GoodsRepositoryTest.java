package com.hanamarket;

import com.hanamarket.product.domain.Goods;
import com.hanamarket.product.domain.GoodsRepository;
import com.hanamarket.product.domain.GoodsStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class GoodsRepositoryTest {

    @Autowired
    private GoodsRepository repository;

    @Test
    void 상품_생성() {
        // Given
        Goods build = Goods.builder()
                .goodsName("goodsName")
                .status(GoodsStatus.NEW)
                .sellPrice(12000)
                .description("description")
                .build();
        // When
        Goods expectProduct = repository.save(build);

        // Then
        assertThat(expectProduct).isNotNull();
    }

}