package org.jiyoung.kikihi.platform.application.service;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.out.jpa.user.CustomMemberDetails;
import org.jiyoung.kikihi.platform.adapter.out.jpa.user.User;
import org.jiyoung.kikihi.platform.adapter.out.jpa.user.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomeMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override// 사용자 email로 사용자 정보를 가져오는 메서드 - 필수로 구현해야함
    public UserDetails loadUserByUsername(String email){
        User user =memberRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException(email));
        if(user !=null){
            return new CustomMemberDetails(user);
        }

        return null;
    }

}
