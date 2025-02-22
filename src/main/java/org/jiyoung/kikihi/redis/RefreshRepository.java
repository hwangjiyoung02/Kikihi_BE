package org.jiyoung.kikihi.redis;

import jakarta.transaction.Transactional;
import org.jiyoung.kikihi.member.domain.RefreshEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshRepository extends CrudRepository<RefreshEntity, Long> {
    Boolean existsByRefresh(String refresh);

    @Transactional
    void deleteByRefresh(String refresh);

    Optional<RefreshEntity> findByRefresh(String refresh);
}
