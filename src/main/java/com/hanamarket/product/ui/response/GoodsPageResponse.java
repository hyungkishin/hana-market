package com.hanamarket.product.ui.response;

import com.hanamarket.product.ui.response.dto.GoodsDto;
import com.hanamarket.product.ui.response.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GoodsPageResponse {

    private List<GoodsDto> contents;

    private int page;

    private int size;

    private long totalElements;

    public static GoodsPageResponse of(List<GoodsDto> data, PageInfo pageInfo) {
        return GoodsPageResponse.builder()
                .contents(data)
                .page(pageInfo.getPageNumber())
                .size(pageInfo.getPageSize())
                .totalElements(pageInfo.getTotalElements())
                .build();
    }

}