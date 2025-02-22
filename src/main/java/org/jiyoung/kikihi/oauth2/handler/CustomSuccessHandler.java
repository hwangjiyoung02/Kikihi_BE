package org.jiyoung.kikihi.oauth2.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.oauth2.CustomOAuth2User;
import org.jiyoung.kikihi.security.util.JWTUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws  ServletException, IOException {
        // OAuthUser
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String userName=oAuth2User.getUsername();

//        jwt를 만들기 위한 role 추출
//        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iterator=authorities.iterator();
//        GrantedAuthority auth= iterator.next();
//        String role=auth.getAuthority();

        String token= jwtUtil.createJwt("access",userName,60*60*60L);
        response.addCookie(createCookie("Authorization",token));
        // 로그인 성공시 처리할 내용
        response.sendRedirect("https://d1t9cmfryhhcaq.cloudfront.net/");
    }


    private Cookie createCookie(String key, String value){
        Cookie cookie=new Cookie(key,value);
        cookie.setMaxAge(24*60*60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);// 자바스크립트에서 접근 불가
        cookie.setSecure(true); // https에서만 전송

        return cookie;
    }


}
