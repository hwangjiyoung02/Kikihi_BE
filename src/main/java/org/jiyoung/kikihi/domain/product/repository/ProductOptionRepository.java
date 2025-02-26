package org.jiyoung.kikihi.domain.product.repository;

import org.jiyoung.kikihi.domain.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
}
