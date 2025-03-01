package org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductOptionJpaRepository extends JpaRepository<ProductOptionJpaEntity, Long> {

    List<ProductOptionJpaEntity> findByProductId(Long productId);

}
