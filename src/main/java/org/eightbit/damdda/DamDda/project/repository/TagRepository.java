package org.eightbit.damdda.DamDda.project.repository;

import org.eightbit.damdda.DamDda.project.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    // 태그 이름으로 태그 엔티티를 조회하는 메서드
    Tag findByName(String name);
}
