package com.hanamarket.product.application;

import com.hanamarket.common.exception.MarketRuntimeException;
import com.hanamarket.common.exception.message.ProductErrorCode;
import com.hanamarket.product.domain.Goods;
import com.hanamarket.product.domain.GoodsRepository;
import com.hanamarket.product.ui.request.GoodsRequest;
import com.hanamarket.product.ui.request.GoodsSearchRequest;
import com.hanamarket.product.ui.response.CreateProductResponse;
import com.hanamarket.product.ui.response.FindProductResponse;
import com.hanamarket.product.ui.response.PageDto;
import com.hanamarket.product.ui.response.dto.GoodsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsService {

    private final GoodsRepository goodsRepository;

    @Transactional
    public CreateProductResponse createGoods(final GoodsRequest request) {
        Goods createProduct = goodsRepository.save(request.toEntity());
        return CreateProductResponse.of(createProduct.getGoodsId());
    }

    public FindProductResponse findGoods(final Long productId) {
        Goods goods = findById(productId);
        return FindProductResponse.of(goods);
    }

    private Goods findById(Long productId) {
        return goodsRepository.findById(productId)
                .orElseThrow(() -> new MarketRuntimeException(ProductErrorCode.NOT_FOUND_PRODUCT, "존재하지 않는 상품입니다"));
    }

    public PageDto<GoodsDto> searchGoods(GoodsSearchRequest searchRequest) {
        Page<Goods> goodsList = goodsRepository.findAll(searchRequest.getSpec(), searchRequest.getPageable());
        return PageDto.of(goodsList, GoodsDto::of);
    }

    @Transactional
    public void update(Long goodsId, GoodsRequest request) {
        Goods goods = findById(goodsId);
        goods.update(request);
    }

}
