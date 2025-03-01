package org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.tag;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTagJpaRepository extends JpaRepository<ProductTagJpaEntity, Long> {
}
