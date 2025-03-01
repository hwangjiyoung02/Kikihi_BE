package org.jiyoung.kikihi.domain.product.repository;


import org.jiyoung.kikihi.domain.product.entity.ProductOption;
import org.jiyoung.kikihi.domain.product.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {
}
