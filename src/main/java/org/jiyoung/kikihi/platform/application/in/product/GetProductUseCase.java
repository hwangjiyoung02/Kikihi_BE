package org.jiyoung.kikihi.platform.application.in.product;

import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GetProductUseCase {

    /*
        사용자가 상품에 대해서 정보를 얻는 유즈케이스
     */

    /*
    상품 상세 조회
    가격 할인 같은 변동성이 높은 데이터는 Redis를 활용하여 수정한다.
    */
    Optional<Product> getProductById(Long productId);

    // 최신순 조회
    Page<Product> getProducts(Pageable pageable);

    // 좋아요수 조회
    Page<Product> getProductsByLike(Pageable pageable);

    // 조건 바탕 조회
    Page<Product> getProductsByCondition(Pageable pageable, String productTitle, Double minPrice, Double maxPrice);

}
