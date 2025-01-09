package org.eightbit.damdda.DamDda.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)// 상속받은 얘만 쓸수 있게
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true,nullable = false)
    private String email;

    private String password;

    @Embedded
    private LoginType loginType;


    private String name;

    private String imageUrl;

    //생성자
    @Builder
    private Member(String email, String password, LoginType loginType, String name, String imageUrl) {
        this.email = email;
        this.password = password;
        this.loginType = loginType;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    //생성 메서드
    public static Member createMember(String email, String password, String name,LoginProvider loginProvider,String socialId, String imageUrl) {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .loginType(LoginType.builder()
                        .loginProvider(loginProvider)
                        .socialId(socialId)
                        .build())
                .imageUrl(imageUrl)
                .build();
    }

    //일시적으로 사용자 샘플 만들기
    public static Member createTemporary(String email,String name,LoginProvider loginProvider,String socialId){
        return Member.builder()
                .email(email)
                .name(name)
                .loginType(LoginType.builder()
                        .loginProvider(loginProvider)
                        .socialId(socialId)
                        .build())
                .build();
    }



    //수정하기
    //비밀번호 변경하기
    //비밀번호 암호화하기

}
