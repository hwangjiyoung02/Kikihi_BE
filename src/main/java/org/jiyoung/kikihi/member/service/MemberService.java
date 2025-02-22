package org.jiyoung.kikihi.member.service;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.member.domain.Member;
import org.jiyoung.kikihi.member.repository.MemberRepository;
import org.jiyoung.kikihi.member.service.request.JwtUserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long joinProcess(JwtUserDTO dto) {
        String email = dto.getEmail();
        Boolean isExist = memberRepository.findByEmail(email).isPresent();

        if (isExist) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        return memberRepository.save(Member.builder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getMemberId();
    }

}
