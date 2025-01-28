package org.eightbit.damdda.DamDda.member.repository;

import org.eightbit.damdda.DamDda.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    //email을 받아 db테이블에서 회원을 조회하는 메서드

    Optional<Member> findByEmail(String email);


}
