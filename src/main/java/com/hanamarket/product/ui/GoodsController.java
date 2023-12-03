package com.hanamarket.product.ui;

import com.hanamarket.product.application.GoodsService;
import com.hanamarket.product.ui.request.CreateProductRequest;
import com.hanamarket.product.ui.request.GoodsSearchRequest;
import com.hanamarket.product.ui.response.CreateProductResponse;
import com.hanamarket.product.ui.response.FindProductResponse;
import com.hanamarket.product.ui.response.GoodsPageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @PostMapping
    private CreateProductResponse createProduct(@Valid @RequestBody final CreateProductRequest request) {
        return goodsService.createGoods(request);
    }

    @GetMapping("/{productId}")
    private FindProductResponse findByProduct(@PathVariable final Long productId) {
        return goodsService.findByGoods(productId);
    }

    @GetMapping("/search")
    public GoodsPageResponse searchProducts(@ModelAttribute GoodsSearchRequest criteria) {
        return goodsService.searchGoods(criteria);
    }

}