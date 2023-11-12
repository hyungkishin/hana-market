package com.hanamarket.product.infrastructure;

import com.hanamarket.product.domain.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long> {
}
