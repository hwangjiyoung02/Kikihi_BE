//package org.eightbit.damdda.DamDda.security.config;
//
//
//import lombok.RequiredArgsConstructor;
////import org.eightbit.damdda.DamDda.security.filter.LoginFilter;
////import org.eightbit.damdda.DamDda.security.util.JWTUtil;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    //authencationManager가 인자로 받을 객체 생성자 주입
//    private final AuthenticationConfiguration authenticationConfiguration;
//    //JWTUtil 주입
////    private final JWTUtil jwtUtil;
//    private AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//    //filterChainProxy
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf((auth) -> auth.disable());
//        http
//                .formLogin((auth) -> auth.disable());
//        http
//                .httpBasic((auth) -> auth.disable());
////        http
////                .authorizeHttpRequests(((auth) -> auth.requestMatchers("/login", "/", "/join").permitAll()
////                        .anyRequest().authenticated()));
//        //필터 추가
//      //  http
//
////                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);
//        http
//                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }
//
//
//
//    //암호화
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
