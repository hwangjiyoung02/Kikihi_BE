//package org.eightbit.damdda.DamDda.security.filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.eightbit.damdda.DamDda.member.domain.CustomMemberDetails;
////import org.eightbit.damdda.DamDda.security.util.JWTUtil;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.util.Collection;
//import java.util.Iterator;
//
//@RequiredArgsConstructor
//public class LoginFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//    //JWTUtil 주입
//    private final JWTUtil jwtUtil;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        // 로그인 시도
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(email, password);
//
//        return authenticationManager.authenticate(authToken);
//    }
//
//    //로그인 성공시 실행하는 메서드(여기서 jwt토큰 생성)
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
//
//        //UserDetailsS
//        CustomMemberDetails customUserDetails = (CustomMemberDetails) authentication.getPrincipal();
//
//        String username = customUserDetails.getUsername();
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//        GrantedAuthority auth = iterator.next();
//
//        String role = auth.getAuthority();
//
//        String token = jwtUtil.createJwt(username, role, 60*60*10L);
//
//        response.addHeader("Authorization", "Bearer " + token);
//    }
//
//    //로그인 실패시 실행하는 메서드
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
//
//        //로그인 실패시 401 응답 코드 반환
//        response.setStatus(401);
//    }
//}
