package com.hanamarket.product.domain;

import com.hanamarket.common.converter.CustomEnumType;
import com.hanamarket.common.converter.CustomType;
import com.hanamarket.product.ui.request.GoodsRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "GOODS")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOODS_ID", nullable = false)
    private Long goodsId;

    @Column(name = "GOODS_NAME", length = 100)
    private String goodsName;

    @Column(name = "VIEW_COUNT")
    private int viewCount;

    @Column(name = "GOODS_STATUS")
    @CustomType(type = CustomEnumType.NAME)
    private GoodsStatus status;

    @Column(name = "SELL_PRICE")
    private int sellPrice;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @CreatedDate
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Builder
    public Goods(String goodsName,
                 int viewCount,
                 GoodsStatus status,
                 int sellPrice,
                 String description) {
        this.goodsName = goodsName;
        this.viewCount = viewCount;
        this.status = status;
        this.sellPrice = sellPrice;
        this.description = description;
    }

    public void update(GoodsRequest request) {
        this.goodsName = request.getGoodsName();
        this.status = request.getStatus();
        this.sellPrice = request.getSellPrice();
        this.description = request.getDescription();
    }

}
