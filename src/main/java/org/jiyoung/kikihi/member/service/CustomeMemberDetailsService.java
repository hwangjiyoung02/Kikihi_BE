package org.jiyoung.kikihi.member.service;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.member.domain.CustomMemberDetails;
import org.jiyoung.kikihi.member.domain.Member;
import org.jiyoung.kikihi.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomeMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override// 사용자 email로 사용자 정보를 가져오는 메서드 - 필수로 구현해야함
    public UserDetails loadUserByUsername(String email){
        Member member=memberRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException(email));
        if(member!=null){
            return new CustomMemberDetails(member);
        }

        return null;
    }

}
