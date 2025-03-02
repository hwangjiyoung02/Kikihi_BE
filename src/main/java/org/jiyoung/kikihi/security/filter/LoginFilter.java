package org.jiyoung.kikihi.security.filter;

import com.nimbusds.oauth2.sdk.token.RefreshToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.domain.user.domain.CustomMemberDetails;
import org.jiyoung.kikihi.domain.user.domain.RefreshEntity;
import org.jiyoung.kikihi.infrastructure.redis.RefreshRepository;
import org.jiyoung.kikihi.security.util.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

//강제로 만든 커스텀 필터~ 로그인 성공시 jwt토큰 생성
//securityConfig에서 필터 추가
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
//    private final RefreshRepository refreshRepository;

    // 클라이언트 검증 요창
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("*: "+email + " " + password);

        // 시큐리티에서 유저 정보를 검증하기 위해 token에 담아야함
        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(email, password);
        System.out.println("**authToken: "+ authToken);

        //authenticationManager가 검증을 진행하게 됨!!!
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메서드(여기서 jwt토큰 생성)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
         // 두개의 토큰을 발급해보겠음
        String email = authentication.getName();
        String role=authentication.getAuthorities().toString();
        System.out.println("successfulAuthentication");

        if (email == null || role == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print("email or role is null");
            return;
        }


        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator=authorities.iterator();
        GrantedAuthority auth= iterator.next();

         //4. refresh,access 토큰 생성
        String access=jwtUtil.createJwt(email,role,600000L);
        String refresh=jwtUtil.createJwt(email,role,84000000L);

//        RefreshToken redis = new RefreshToken(refresh);
//        RefreshEntity refreshEntity = RefreshEntity.builder()
//                .username(email)
//                .refresh(redis.getValue())
//                .build();
//        refreshRepository.save(refreshEntity);

//        System.out.println("[loginfilter] 발급된 Access Token: " + access);

//
        //응답 설정
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("access",access);
        response.addCookie(createCookie("refresh",refresh));
        response.setStatus(HttpStatus.OK.value());


        //UserDetailsS
        CustomMemberDetails customUserDetails = (CustomMemberDetails) authentication.getPrincipal();
        String token = jwtUtil.createJwt(email, role,60*60*100L);
        response.addHeader("Authorization", "Bearer " + token);
        System.out.println("로그인 성공");

    }


    //로그인 실패시 실행하는 메서드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        System.out.println("로그인 실패");

        //로그인 실패시 401 응답 코드 반환
        response.setStatus(401);
    }

    //토큰 심화
    private Cookie createCookie(String key, String value){
        Cookie cookie=new Cookie(key,value);
        cookie.setMaxAge(24*60*60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);// 자바스크립트에서 접근 불가
        cookie.setSecure(true); // https에서만 전송

        return cookie;
    }
}
