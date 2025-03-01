package org.jiyoung.kikihi.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.domain.user.domain.User;
import org.jiyoung.kikihi.domain.user.repository.MemberRepository;
import org.jiyoung.kikihi.domain.user.dto.JoinRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 가입하고 도대체 뭘 반환해야하는 거임..???
    public void joinProcess(JoinRequest dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        String name=dto.getName();

        System.out.println(email+","+password+","+name);
        boolean isExist = memberRepository.findByEmail(email).isPresent();

        if (isExist) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        User user= memberRepository.save(User.from(email, bCryptPasswordEncoder.encode(password), name));
        System.out.println(user.getEmail()+":"+user.getPassword()+":"+user.getRole());
    }

}
