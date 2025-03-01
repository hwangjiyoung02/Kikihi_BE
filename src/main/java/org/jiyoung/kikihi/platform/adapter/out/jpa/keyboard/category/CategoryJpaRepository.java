package org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long> {

    Optional<CategoryJpaEntity> findByCode(String code);

    boolean existsByCode(String code);


}