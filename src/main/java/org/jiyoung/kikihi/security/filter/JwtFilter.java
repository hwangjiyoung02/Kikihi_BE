package org.jiyoung.kikihi.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.domain.user.domain.CustomMemberDetails;
import org.jiyoung.kikihi.domain.user.domain.User;
import org.jiyoung.kikihi.security.util.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.PrintWriter;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil; // JWT 검증 메서드가 포함된 유틸리티 클래스

    // HTTP 요청을 필터링하여 JWT 검증을 처리하는 메서드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
  
        // 토큰 있는지 없는지 검증 -> 없으면 종료
//        String authorization= request.getHeader("Authorization");
//        if (authorization == null || !authorization.startsWith("Bearer ")) {
//
//            System.out.println("token없음");
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // 토큰이 있기 때문에 토큰을 추출할 수 있음
//        System.out.println("검증 시작");
//        String token = authorization.substring(7);
//
//        // 토큰은 있는데 유효시간 만료 -> 종료
//        if(jwtUtil.isExpired(token)){
//            System.out.println("토큰 유효시간 만료");
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // 토큰 1차 검증이 완료됨 -> 이메일, role 추출
//        String email=jwtUtil.getEmail(token);
//        String role=jwtUtil.getRole(token);
//
//        //User객체의 정보를 UserDetail에 담기
//        CustomMemberDetails customMemberDetails=new CustomMemberDetails(User.from(email,role));
//        //인증에 필요한 정보를 담아서 Authentication 객체를 생성 -> AuthenticationManager와 연계되어 이제 진짜 인증 요청을 할 수 있음
//        // 인증이 성공하면 Authentication객체를 반환하며 securityContext에 저장하여 저장된 사용자 정보 전달
//        Authentication authentication = new UsernamePasswordAuthenticationToken(email,customMemberDetails);
//        System.out.println("authorization 성공: "+authorization);
//        // 세션에 사용자 등록
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(request, response);
        
        


   // Authrization헤더에서 토큰 추출
        // 토큰 있는지 없는지 검증 -> 없으면 종료

        // 얘는 헤더
//        String authorization= request.getHeader("Authorization");
//        if (authorization == null || !authorization.startsWith("Bearer ")) {
//
//            System.out.println("token없음");
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // 토큰이 있기 때문에 토큰을 추출할 수 있음
//        System.out.println("검증 시작");
//        String token = authorization.substring(7);


        // 헤더에서 access키에 담긴 토큰을 꺼냄
        String token = request.getHeader("access");

        // 토큰이 없다면 다음 필터로 넘김
        if (token == null) {

            filterChain.doFilter(request, response);

            return;
        }

        try {
            jwtUtil.isExpired(token);  // 토큰이 만료되었는지 확인
            PrintWriter pw = response.getWriter();
            pw.print("access token expired");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (ExpiredJwtException e) {
            // 만약 토큰이 만료되었으면 "access token expired" 메시지를 응답 본문에 작성
            PrintWriter writer = response.getWriter();
            writer.print("invalid token");
            // 응답 상태 코드를 401(Unauthorized)로 설정 -> 리프레시 토큰으로 재발급 받아야되는거 아님???
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // JWT 토큰에서 클레임 추출
        String role = jwtUtil.getRole(token);
        String email = jwtUtil.getEmail(token);

        // JWT 토큰의 페이로드에 명시된 이메일이 "access"와 일치하지 않으면, 유효하지 않은 토큰으로 처리
        if (!email.equals("access")) {
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 사용자 정보를 기반으로 user객체 생성
        // JWT 토큰에서 이메일을 추출하고, 임시로 비밀번호를 설정하여 User 객체를 생성
        User user = User.builder().email(email).password("temppass").build();
        CustomMemberDetails customMemberDetails = new CustomMemberDetails(user);

        // Spring Security의 인증 토큰 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(customMemberDetails, null, customMemberDetails.getAuthorities());

        // 인증 정보를 SecurityContext에 저장하여, 이후 인증 정보에 접근할 수 있도록 함
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 필터 체인에서 다음 필터로 요청을 전달
        filterChain.doFilter(request, response);
    }


}
