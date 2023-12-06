package com.hanamarket.product.ui.request;

import com.hanamarket.common.annotation.ColumnDescription;
import com.hanamarket.product.domain.Goods;
import com.hanamarket.product.domain.GoodsStatus;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GoodsSearchRequest {

    @ColumnDescription("현재페이지")
    @NotNull
    private Integer page;

    @ColumnDescription("페이지당 건수")
    @NotNull
    private Integer size;

    @ColumnDescription("시작 일자")
    private String startDate;

    @ColumnDescription("종료 일자")
    private String endDate;

    @ColumnDescription("최소 금액")
    private int minPrice;

    @ColumnDescription("최대 금액")
    private int maxPrice;

    @ColumnDescription("상품 상태")
    private GoodsStatus status;

    public Integer getPage() {
        if (page == null) {
            return 1;
        }
        return this.page;
    }

    public Integer getSize() {
        if (size == null) {
            return 10;
        }
        return this.size;
    }

    public Specification<Goods> withSearchParameters() {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (startDate != null) {
                LocalDateTime parsedStartDate = parseDate(startDate);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), parsedStartDate));
            }

            if (endDate != null) {
                LocalDateTime parsedEndDate = parseDate(endDate);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), parsedEndDate));
            }

            if (minPrice >= 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellPrice"), minPrice));
            }

            if (maxPrice >= 0) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellPrice"), maxPrice));
            }

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);

            return criteriaBuilder.and(predicatesArray);
        };
    }

    private LocalDateTime parseDate(String date) {
        return LocalDateTime.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MIN);
    }


}
