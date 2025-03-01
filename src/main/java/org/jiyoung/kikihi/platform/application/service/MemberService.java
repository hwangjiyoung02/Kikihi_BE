package org.jiyoung.kikihi.platform.application.service;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.out.jpa.user.User;
import org.jiyoung.kikihi.platform.adapter.out.jpa.user.MemberRepository;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.JwtUserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 가입하고 도대체 뭘 반환해야하는 거임..???
    public void joinProcess(JwtUserDTO dto) {
        String email = dto.getEmail();
        Boolean isExist = memberRepository.findByEmail(email).isPresent();

        if (isExist) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        memberRepository.save(User.from(dto.getEmail(), dto.getPassword()));
    }

}
