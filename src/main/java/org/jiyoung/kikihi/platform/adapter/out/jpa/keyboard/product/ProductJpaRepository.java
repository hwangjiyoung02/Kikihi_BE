package org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {

    // 좋아요 목록 조회
//    List<Product> findTopLikedProducts();

    // 페이징과 정렬을 고려한 상품 목록 조회
    Page<ProductJpaEntity> findAll(Specification<ProductJpaEntity> specification, Pageable pageable);

    // 최신순 조회
    Page<ProductJpaEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // 좋아요순 조회
    Page<ProductJpaEntity> findAllByOrderByStatistics_LikeCountDesc(Pageable pageable);
}
