package com.hanamarket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long> {
}
