package com.hanamarket;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;


    @Test
    void 상품_생성() {
        // Given
        CreateProductRequest createProductRequest = new CreateProductRequest("상품1", ProductStatus.NEW, 10000, "싸게 팔아요");

        // When
        ProductJpaEntity expectProduct = repository.save(createProductRequest.toEntity());

        // Then
        assertThat(expectProduct).isNotNull();
    }

}