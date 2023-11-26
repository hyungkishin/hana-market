package com.hanamarket.product.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<GoodsJpaEntity, Long> {

    Page<GoodsJpaEntity> findAll(Specification<GoodsJpaEntity> spec, Pageable pageable);

}
