package org.eightbit.damdda.DamDda.member.service;

import lombok.RequiredArgsConstructor;
import org.eightbit.damdda.DamDda.member.domain.Member;
import org.eightbit.damdda.DamDda.member.repository.MemberRepository;
import org.eightbit.damdda.DamDda.member.service.request.AddUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long joinProcess(AddUserRequest dto) {
        String email = dto.getEmail();
        Boolean isExist = memberRepository.findByEmail(email).isPresent();

        if (isExist) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        return memberRepository.save(Member.builder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .build()).getMemberId();
    }

}
