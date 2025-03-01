package org.jiyoung.kikihi.platform.adapter.out.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    User findByName(String name);
}
