package org.jiyoung.kikihi.platform.adapter.out.jpa.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)// 상속받은 얘만 쓸수 있게
@AllArgsConstructor
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Setter
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = true)
    private String password;

    //setter
    @Setter
    @Column(nullable = true)
    private String name;

    @Setter
    @Column(nullable = true)
    private String userName;

    //생성자
    @Builder
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }


    //생성 메서드
    public static User from(String email, String password){
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }




    //수정하기
    //비밀번호 변경하기
    //비밀번호 암호화하기

}
