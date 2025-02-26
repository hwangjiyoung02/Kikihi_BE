package org.jiyoung.kikihi.domain.product.repository;

import org.jiyoung.kikihi.domain.product.entity.LikedProduct;
import org.jiyoung.kikihi.domain.product.entity.LikedProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedProductRepository extends JpaRepository<LikedProduct, LikedProductId> {
}
