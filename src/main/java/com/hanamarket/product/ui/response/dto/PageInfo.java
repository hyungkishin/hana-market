package com.hanamarket.product.ui.response.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class PageInfo {

    private int pageNumber;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    public static PageInfo of(Page page) {
        return PageInfo.builder()
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

}
