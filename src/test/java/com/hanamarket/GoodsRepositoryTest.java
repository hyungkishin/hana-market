//package com.hanamarket;
//
//import com.hanamarket.base.BaseDataJpaTest;
//import com.hanamarket.product.domain.Goods;
//import com.hanamarket.product.domain.GoodsRepository;
//import com.hanamarket.product.domain.GoodsStatus;
//import jakarta.persistence.EntityManager;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ActiveProfiles("test")
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
//class GoodsRepositoryTest {
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @Autowired
//    private GoodsRepository repository;
//
//    @Test
//    void 상품_생성() {
//        // Given
//        Goods build = Goods.builder()
//                .goodsName("goodsName")
//                .status(GoodsStatus.NEW)
//                .sellPrice(12000)
//                .description("description")
//                .build();
//        // When
////        Goods expectProduct = repository.save(build);
//        Goods expectProduct = testEntityManager.persist(build);
//        // Then
//        assertThat(expectProduct).isNotNull();
//    }
//
//}