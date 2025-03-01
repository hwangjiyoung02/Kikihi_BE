package org.jiyoung.kikihi.platform.application.out.keyboard.product;

import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LoadProductPort {

    // 상품 정보 가져오기
    Optional<Product> loadProductById(Long productId);

    // 최신순 가져오기
    Page<Product> loadProducts(Pageable pageable);

    // 좋아요수 조회
    Page<Product> loadProductsByLike(Pageable pageable);

    // 조건에 따른 조히
    Page<Product> loadProductsByCondition(Pageable pageable, String productTitle, Double minPrice, Double maxPrice);

}
