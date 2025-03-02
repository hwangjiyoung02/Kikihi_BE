package org.jiyoung.kikihi.common.security.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor  // 생성자 자동 생성: final 필드를 가지는 생성자를 생성
public class CustomUserDetails implements UserDetails {
    private final org.jiyoung.kikihi.domain.user.domain.UserJpaEntity user;  // User 객체를 포함하여 사용자 정보를 담고 있음

    // 사용자의 권한 정보를 반환하는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 정보를 담을 컬렉션 생성
        Collection<GrantedAuthority> collection = new ArrayList<>();

        // 사용자의 역할을 권한으로 추가
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();  // 사용자 역할 반환
            }
        });
        return collection;  // 권한 리스트 반환
    }

    // 사용자 비밀번호를 반환하는 메서드
    @Override
    public String getPassword() {
        return user.getPassword();  // 사용자 비밀번호 반환
    }

    // 사용자 이메일(사용자명)을 반환하는 메서드
    @Override
    public String getUsername() {
        return user.getEmail();  // 사용자 이메일 반환
    }

    // 계정이 만료되지 않았는지 확인하는 메서드
    @Override
    public boolean isAccountNonExpired() {
        return true;  // 계정이 만료되지 않음 (항상 true 반환)
    }

    // 계정이 잠기지 않았는지 확인하는 메서드
    @Override
    public boolean isAccountNonLocked() {
        return true;  // 계정이 잠기지 않음 (항상 true 반환)
    }

    // 자격 증명이 만료되지 않았는지 확인하는 메서드
    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // 자격 증명이 만료되지 않음 (항상 true 반환)
    }

    // 계정이 활성화되었는지 확인하는 메서드
    @Override
    public boolean isEnabled() {
        return true;  // 계정이 활성화됨 (항상 true 반환)
    }
}
