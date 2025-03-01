package org.jiyoung.kikihi.domain.product.repository;

import org.jiyoung.kikihi.domain.product.entity.LikedProduct;
import org.jiyoung.kikihi.domain.product.entity.LikedProductId;
import org.jiyoung.kikihi.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LikedProductRepository extends JpaRepository<LikedProduct, LikedProductId> {
   // 마이페이지에서 좋아요 목록 가져오기
}
