package org.jiyoung.kikihi.product.repository;

import org.jiyoung.kikihi.product.domain.LikedProduct;
import org.jiyoung.kikihi.product.domain.LikedProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedProductRepository extends JpaRepository<LikedProduct, LikedProductId> {
}
