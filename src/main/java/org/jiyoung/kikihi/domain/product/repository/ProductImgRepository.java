package org.jiyoung.kikihi.domain.product.repository;

import org.jiyoung.kikihi.domain.product.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImg,Long> {
}
