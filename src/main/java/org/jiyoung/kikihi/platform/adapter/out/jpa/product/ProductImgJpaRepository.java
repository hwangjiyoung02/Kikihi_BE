package org.jiyoung.kikihi.platform.adapter.out.jpa.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgJpaRepository extends JpaRepository<ProductImg,Long> {
}
