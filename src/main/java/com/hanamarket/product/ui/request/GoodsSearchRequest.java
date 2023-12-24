package com.hanamarket.product.ui.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanamarket.common.annotation.ColumnDescription;
import com.hanamarket.product.domain.Goods;
import com.hanamarket.product.domain.GoodsStatus;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

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
    @NotNull(message = "page 는 필수 값 입니다")
    private Integer page;

    @ColumnDescription("페이지당 건수")
    @NotNull(message = "size 는 필수 값 입니다")
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

    public boolean hasOtherCriteria() {
        return !ObjectUtils.isEmpty(startDate)
                || !ObjectUtils.isEmpty(endDate)
                || (minPrice != 0 || maxPrice != 0)
                || status != null;
    }

    private boolean hasMinMaxSellPrice() {
        return ! (minPrice == 0 && maxPrice == 0);
    }

    private boolean hasStartAtAndEndAt() {
        return !ObjectUtils.isEmpty(startDate) && !ObjectUtils.isEmpty(endDate);
    }

    @JsonIgnore
    public Specification<Goods> getSpec() {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (hasStartAtAndEndAt()) {
                LocalDateTime startAt = parseDate(startDate).atTime(LocalTime.MIN);
                LocalDateTime endAt = parseDate(endDate).atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get("createdAt"), startAt, endAt));
            }

            if (hasMinMaxSellPrice()) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellPrice"), minPrice));
                predicates.add(criteriaBuilder.between(root.get("sellPrice"), minPrice, maxPrice));
            }

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(getPage() - 1, getSize(), Sort.by("goodsId").descending());
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
