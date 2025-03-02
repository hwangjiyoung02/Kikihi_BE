package org.jiyoung.kikihi.platform.adapter.out.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedProductJpaRepository extends JpaRepository<LikedProduct, LikedProductId> {
   // 마이페이지에서 좋아요 목록 가져오기
}
