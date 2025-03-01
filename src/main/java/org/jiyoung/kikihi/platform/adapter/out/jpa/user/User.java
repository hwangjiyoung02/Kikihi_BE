package org.jiyoung.kikihi.platform.adapter.out.jpa.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)// 상속받은 얘만 쓸수 있게
@AllArgsConstructor
@Getter
@Builder
public class User implements UserDetails {
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

    //권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override// 만료되었는지 확인하는 로직
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override// 계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override// 패스워드 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override//계정 사용 가능 여부 반환
    public boolean isEnabled() {
        return true;
    }

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
