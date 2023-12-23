package com.hanamarket.product.ui;

import com.hanamarket.product.application.GoodsService;
import com.hanamarket.product.ui.request.GoodsRequest;
import com.hanamarket.product.ui.request.GoodsSearchRequest;
import com.hanamarket.product.ui.response.CreateProductResponse;
import com.hanamarket.product.ui.response.FindProductResponse;
import com.hanamarket.product.ui.response.PageDto;
import com.hanamarket.product.ui.response.dto.GoodsDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @PostMapping
    private CreateProductResponse createProduct(@Valid @RequestBody final GoodsRequest request) {
        return goodsService.createGoods(request);
    }

    @GetMapping("/{goodsId}")
    private FindProductResponse findByProduct(@PathVariable final Long goodsId) {
        return goodsService.findGoods(goodsId);
    }

    @GetMapping
    public PageDto<GoodsDto> searchProducts(@ModelAttribute GoodsSearchRequest request) {
        return goodsService.searchGoods(request);
    }

    @PutMapping("/{goodsId}")
    private void updateGoods(@PathVariable final Long goodsId, @RequestBody GoodsRequest request) {
        goodsService.update(goodsId, request);
    }

}
