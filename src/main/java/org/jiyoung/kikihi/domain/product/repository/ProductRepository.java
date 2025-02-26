package org.jiyoung.kikihi.domain.product.repository;

import org.jiyoung.kikihi.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 페이징과 정렬을 고려한 상품 목록 조회
//    Page<Product> findAll(Pageable pageable);

    // 좋아요가 많은 상품 조회 (예시)
//    List<Product> findTopLikedProducts(); // 예시로 Top 10개의 좋아요가 많은 상품을 반환한다고 가정

}
