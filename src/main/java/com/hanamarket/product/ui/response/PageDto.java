package com.hanamarket.product.ui.response;

import com.hanamarket.common.annotation.ColumnDescription;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PageDto<T> {
    @ColumnDescription("데이터 목록")
    private List<T> content;

    @ColumnDescription("전체 페이지 수")
    private Integer totalPages;

    @ColumnDescription("전체 개체 수")
    private Long totalElements;

    @ColumnDescription("첫 페이지 여부")
    private Boolean first;

    @ColumnDescription("마지막 페이지 여부")
    private Boolean last;

    private Boolean empty;

    public static <E, T> PageDto<T> of(Page<E> pageData,
                                       Function<E, T> entityToDtoConverter) {

        final Page<T> map = pageData.map(entityToDtoConverter);

        return PageDto.<T>builder()
                .content(map.getContent())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .empty(pageData.isEmpty())
                .first(pageData.isFirst())
                .last(pageData.isLast())
                .build();
    }

}