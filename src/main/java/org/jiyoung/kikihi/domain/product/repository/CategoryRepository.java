package org.jiyoung.kikihi.domain.product.repository;

import org.jiyoung.kikihi.domain.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}