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
        String authorization= request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            System.out.println("token없음");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰이 있기 때문에 토큰을 추출할 수 있음
        System.out.println("검증 시작");
        String token = authorization.substring(7);
        
        // 토큰은 있는데 유효시간 만료 -> 종료
        if(jwtUtil.isExpired(token)){
            System.out.println("토큰 유효시간 만료");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 1차 검증이 완료됨 -> 이메일, role 추출
        String email=jwtUtil.getEmail(token);
        String role=jwtUtil.getRole(token);

        //User객체의 정보를 UserDetail에 담기
        CustomMemberDetails customMemberDetails=new CustomMemberDetails(User.from(email,role));
        //인증에 필요한 정보를 담아서 Authentication 객체를 생성 -> AuthenticationManager와 연계되어 이제 진짜 인증 요청을 할 수 있음
        // 인증이 성공하면 Authentication객체를 반환하며 securityContext에 저장하여 저장된 사용자 정보 전달
        Authentication authentication = new UsernamePasswordAuthenticationToken(email,customMemberDetails);
        System.out.println("authorization 성공: "+authorization);
        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
        
        
        
        
        
        
        // 1.  요청 헤더에서 "access"라는 이름으로 JWT 토큰을 추출
//        String accessToken = request.getHeader("access");
//        // 토큰이 없다면, 다음 필터로 요청을 전달하고 종료 -> 엑세스 토큰이 없으면 refresh토큰으로 재발급 여부 확인
//        if (accessToken == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        // 1-2. jwt 토큰의 만료 여부를 체크, 만약 만료되었다면 응답으로 오류 메시지를 반환하고 필터 체인을 종료
//        try {
//            jwtUtil.isExpired(accessToken);  // 토큰이 만료되었는지 확인
//        } catch (ExpiredJwtException e) {
//            // 만약 토큰이 만료되었으면 "access token expired" 메시지를 응답 본문에 작성
//            PrintWriter writer = response.getWriter();
//            writer.print("access token expired");
//            // 응답 상태 코드를 401(Unauthorized)로 설정 -> 리프레시 토큰으로 재발급 받아야되는거 아님???
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }

//        // 2. JWT 토큰에서 이메일 정보를 추출
//        String email = jwtUtil.getEmail(accessToken);
//
//        // JWT 토큰의 페이로드에 명시된 이메일이 "access"와 일치하지 않으면, 유효하지 않은 토큰으로 처리
//        if (!email.equals("access")) {
//            // 잘못된 토큰일 경우 "invalid access token" 메시지를 응답 본문에 작성
//            PrintWriter writer = response.getWriter();
//            writer.print("invalid access token");
//
//            // 응답 상태 코드를 401(Unauthorized)로 설정
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        // JWT 토큰에서 이메일을 추출하고, 임시로 비밀번호를 설정하여 User 객체를 생성
//        String jwtUtilEmail = jwtUtil.getEmail(accessToken);
//        User user = User.builder().email(email).password("temppass").build();
//
//        // 사용자 정보를 기반으로 CustomMemberDetails 객체 생성
//        CustomMemberDetails customMemberDetails = new CustomMemberDetails(user);
//
//        // Spring Security의 인증 토큰 생성
//        Authentication authToken = new UsernamePasswordAuthenticationToken(customMemberDetails, null, customMemberDetails.getAuthorities());
//
//        // 인증 정보를 SecurityContext에 저장하여, 이후 인증 정보에 접근할 수 있도록 함
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        // 필터 체인에서 다음 필터로 요청을 전달
//        filterChain.doFilter(request, response);
    }
}
