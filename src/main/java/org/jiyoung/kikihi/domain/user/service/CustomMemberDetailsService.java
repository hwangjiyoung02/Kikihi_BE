package org.jiyoung.kikihi.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.domain.user.domain.CustomMemberDetails;
import org.jiyoung.kikihi.domain.user.domain.User;
import org.jiyoung.kikihi.domain.user.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override// 사용자 email로 사용자 정보를 가져오는 메서드 - 필수로 구현해야함
    public UserDetails loadUserByUsername(String email){
        System.out.println("!loadUserByUsername: "+email);
        User user =memberRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException(email));
        System.out.println("!!loadUserByUsername: "+user);
        if(user !=null){
            //UserDetails에 담아서 return하면 AutneticationManager가 검증함
            return new CustomMemberDetails(user);
        }

        return null;
    }

}
