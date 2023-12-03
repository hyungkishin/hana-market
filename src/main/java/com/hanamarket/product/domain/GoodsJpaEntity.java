package com.hanamarket.product.domain;

import com.hanamarket.common.converter.CustomEnumType;
import com.hanamarket.common.converter.CustomType;
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
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "goods")
public class GoodsJpaEntity {

    @Id
    @GeneratedValue // (strategy = GenerationType.IDENTITY) todo mysql
    @Column(name = "goods_id", columnDefinition = "BIGINT COMMENT '상품 Id'")
    private Long id;

    @Embedded
    private GoodsName goodsName;

    @Column(name = "view_count", columnDefinition = "INT COMMENT '상품 조회수'")
    private int viewCount;

    @CustomType(type = CustomEnumType.NAME)
    @Column(name = "status", length = 20, columnDefinition = "VARCHAR(20) COMMENT '상품 상태'")
    private GoodsStatus status;

    @Column(name = "sell_price", columnDefinition = "INT COMMENT '상품 가격'")
    private int sellPrice;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT COMMENT '상품 설명'")
    private String description;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP COMMENT '생성 일시'")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP COMMENT '수정 일시'")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Builder
    public GoodsJpaEntity(GoodsName goodsName,
                          GoodsStatus status,
                          int sellPrice,
                          String description) {
        this.goodsName = goodsName;
        this.status = status;
        this.sellPrice = sellPrice;
        this.description = description;
    }

    public String getGoodsName() {
        return goodsName.getValue();
    }

}
