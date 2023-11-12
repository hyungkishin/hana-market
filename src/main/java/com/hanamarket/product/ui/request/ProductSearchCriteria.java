package com.hanamarket.product.ui.request;

import lombok.Getter;

import java.time.LocalDate;


@Getter
public class ProductSearchCriteria {

    private int page;

    private int size;

    private LocalDate startDate;

    private LocalDate endDate;

    private int minPrice;

    private int maxPrice;

    private String status;

}
