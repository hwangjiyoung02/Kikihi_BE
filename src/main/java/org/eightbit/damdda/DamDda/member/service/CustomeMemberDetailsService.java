package org.eightbit.damdda.DamDda.member.service;

import lombok.RequiredArgsConstructor;
import org.eightbit.damdda.DamDda.member.domain.Member;
import org.eightbit.damdda.DamDda.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomeMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override// 사용자 email로 사용자 정보를 가져오는 메서드 - 필수로 구현해야함
    public Member loadUserByUsername(String email){
        Member member=memberRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException(email));
        if(member!=null){
            return member;
        }

        return null;
    }

}
