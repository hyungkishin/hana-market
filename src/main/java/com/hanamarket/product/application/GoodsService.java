package com.hanamarket.product.application;

import com.hanamarket.common.exception.MarketRuntimeException;
import com.hanamarket.common.exception.message.ProductErrorCode;
import com.hanamarket.product.domain.Goods;
import com.hanamarket.product.domain.GoodsRepository;
import com.hanamarket.product.ui.request.CreateProductRequest;
import com.hanamarket.product.ui.request.GoodsSearchRequest;
import com.hanamarket.product.ui.response.CreateProductResponse;
import com.hanamarket.product.ui.response.FindProductResponse;
import com.hanamarket.product.ui.response.GoodsPageResponse;
import com.hanamarket.product.ui.response.dto.GoodsDto;
import com.hanamarket.product.ui.response.dto.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsService {

    private final GoodsRepository goodsRepository;

    @Transactional
    public CreateProductResponse createGoods(final CreateProductRequest request) {
        Goods createProduct = goodsRepository.save(request.toEntity());

        return CreateProductResponse.of(createProduct.getGoodsId());
    }

    public FindProductResponse findByGoods(final Long productId) {
        Goods goods = goodsRepository.findById(productId)
                .orElseThrow(() -> new MarketRuntimeException(ProductErrorCode.NOT_FOUND_PRODUCT, "존재하지 않는 상품입니다"));

        return FindProductResponse.of(goods);
    }

    public GoodsPageResponse searchGoods(GoodsSearchRequest searchRequest) {
        Specification<Goods> spec = searchRequest.withSearchParameters();
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());

        Page<Goods> resultPage = goodsRepository.findAll(spec, pageRequest);

        List<GoodsDto> goodsDtoList = resultPage.getContent()
                .stream()
                .map(GoodsDto::fromEntity)
                .collect(Collectors.toList());

        PageInfo pageInfo = PageInfo.of(resultPage);

        return GoodsPageResponse.of(goodsDtoList, pageInfo);
    }

}
