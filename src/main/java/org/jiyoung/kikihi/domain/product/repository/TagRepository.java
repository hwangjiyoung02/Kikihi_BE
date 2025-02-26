package org.jiyoung.kikihi.domain.product.repository;

import org.jiyoung.kikihi.domain.product.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
