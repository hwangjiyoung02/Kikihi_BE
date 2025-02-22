package org.jiyoung.kikihi.security.filter;

import com.nimbusds.oauth2.sdk.token.RefreshToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.member.domain.RefreshEntity;
import org.jiyoung.kikihi.redis.RefreshRepository;
import org.jiyoung.kikihi.security.util.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

//강제로 만든 커스텀 필터~ 로그인 성공시 jwt토큰 생성
//securityConfig에서 필터 추가
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    //주입
    private final AuthenticationManager authenticationManager;

    private final RefreshRepository refreshRepository;
    //JWTUtil 주입
    private final JWTUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
         //로그인 시도
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("*: "+email + " " + password);

        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(email, password);
        System.out.println("**authToken: "+ authToken);

        //authenticationManager가 검증을 진행하게 됨
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메서드(여기서 jwt토큰 생성)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
         // 주석 처리하고 두개의 토큰을 발급해보겠음

        String email = authentication.getName();

        if (email == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print("Email is null");
            return;
        }
         //4. refresh,access 토큰 생성
        String access=jwtUtil.createJwt("access",email,600000L);
        String refresh=jwtUtil.createJwt("refresh",email,84000000L);
        RefreshToken redis = new RefreshToken(refresh);
        RefreshEntity refreshEntity = RefreshEntity.builder()
                .username(email)
                .refresh(redis.getValue())
                .build();
        refreshRepository.save(refreshEntity);

        System.out.println("[loginfilter] 발급된 Access Token: " + access);


        //응답 설정
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Authorization", "Bearer " + access);
        response.addCookie(createCookie("refresh",refresh));
        response.setStatus(HttpStatus.OK.value());


        //UserDetailsS
//        CustomMemberDetails customUserDetails = (CustomMemberDetails) authentication.getPrincipal();
//        String token = jwtUtil.createJwt(email, 60*60*10L);
//        response.addHeader("Authorization", "Bearer " + token);
//        response.addCookie(createRefreshCookie(refreshToken));
        System.out.println("로그인 성공");

    }


    //로그인 실패시 실행하는 메서드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        System.out.println("로그인 실패");

        //로그인 실패시 401 응답 코드 반환
        response.setStatus(401);
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
