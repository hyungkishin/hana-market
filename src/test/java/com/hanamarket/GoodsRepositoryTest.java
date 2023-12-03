package com.hanamarket;

import com.hanamarket.product.domain.GoodsJpaEntity;
import com.hanamarket.product.domain.GoodsRepository;
import com.hanamarket.product.domain.GoodsStatus;
import com.hanamarket.product.ui.request.CreateProductRequest;
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
        CreateProductRequest createProductRequest = new CreateProductRequest("상품1", GoodsStatus.NEW, 10000, "싸게 팔아요");

        // When
        GoodsJpaEntity expectProduct = repository.save(createProductRequest.toEntity());

        // Then
        assertThat(expectProduct).isNotNull();
    }

}