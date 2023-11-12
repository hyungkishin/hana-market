package com.hanamarket.product.infrastructure;

import com.hanamarket.product.domain.ProductJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long> {

    Page<ProductJpaEntity> findByCreatedAtBetweenAndSellPriceBetweenAndStatus(
            LocalDate startDate, LocalDate endDate,
            int minPrice, int maxPrice,
            String status, Pageable pageable);

}
