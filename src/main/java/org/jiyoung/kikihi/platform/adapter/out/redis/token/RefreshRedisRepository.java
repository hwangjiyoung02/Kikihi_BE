package org.jiyoung.kikihi.platform.adapter.out.redis.token;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshRedisRepository extends CrudRepository<RefreshTokenRedisHash, Long> {
    Boolean existsByRefresh(String refresh);

    @Transactional
    void deleteByRefresh(String refresh);

    Optional<RefreshTokenRedisHash> findByRefresh(String refresh);
}
