package org.jiyoung.kikihi.platform.adapter.out.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<org.jiyoung.kikihi.domain.user.domain.UserJpaEntity, Long>{
    Optional<org.jiyoung.kikihi.domain.user.domain.UserJpaEntity> findByEmail(String email);
    org.jiyoung.kikihi.domain.user.domain.UserJpaEntity findByName(String name);
}
