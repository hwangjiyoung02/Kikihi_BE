//package org.jiyoung.kikihi.common.config;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.jiyoung.kikihi.platform.adapter.out.redis.token.RefreshRedisRepository;
////import org.jiyoung.kikihi.platform.adapter.out.oauth2.CustomOAuth2UserService;
////import org.jiyoung.kikihi.common.security.handler.CustomSuccessHandler;
////import org.jiyoung.kikihi.common.security.filter.CustomLogoutFilter;
////import org.jiyoung.kikihi.common.security.filter.JwtFilter;
////import org.jiyoung.kikihi.common.security.util.JWTUtil;
////import org.jiyoung.kikihi.common.security.filter.LoginFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import org.springframework.security.web.authentication.logout.LogoutFilter;
////import org.springframework.web.cors.CorsConfiguration;
////import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.util.Collections;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
////    //authencationManager가 인자로 받을 객체 생성자 주입
////    private final AuthenticationConfiguration authenticationConfiguration;
////    //CustomOAuth2UserService 주입
////    private final CustomOAuth2UserService customOAuth2UserService;
////    //핸들러 주입
////    private final CustomSuccessHandler customSuccessHandler;
////    //JWTUtil 주입
////    private final JWTUtil jwtUtil;
////    //refreshRepository 주입
////    private final RefreshRedisRepository refreshRedisRepository;
////
////    private AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
////        return authenticationConfiguration.getAuthenticationManager();
////    }
////    //filterChainProxy ->순서가 중요!
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////
////        http
////                .cors((corsCustomizer) -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
////                    @Override
////                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
////                            CorsConfiguration configuration=new CorsConfiguration();
////
////                            configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
////                            configuration.setAllowedMethods(Collections.singletonList("*"));
////                            configuration.setAllowCredentials(true);;
////                            configuration.setAllowedHeaders(Collections.singletonList("*"));
////                            configuration.setMaxAge(3600L);
////
////                            configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));// 쿠키도 추가해줌
////                            configuration.setExposedHeaders(Collections.singletonList("Authorization"));
////                            return configuration;
////                    }
////                }));
////        http
////                .csrf((auth) -> auth.disable());
////        http
////                .formLogin((auth) -> auth.disable());
////        http
////                .httpBasic((auth) -> auth.disable());
////        //oauth2
////        http
////                .oauth2Login((oauth2)->oauth2
////                        .userInfoEndpoint((userInfoEndpointConfig -> userInfoEndpointConfig
////                        .userService(customOAuth2UserService)))
////                        .successHandler(customSuccessHandler)
////                        );
////
////        // 경로별 인가 작업
////        http
////                .authorizeHttpRequests(((auth) -> auth.requestMatchers("/**","/h2-console/**","/join",
////                                "/login","/","/reissue","/oauth2/**").permitAll()
////                        .anyRequest().authenticated()));
////        //필터 추가
////        http
////                .addFilterBefore(new JwtFilter(jwtUtil),LoginFilter.class);
////        //UsernamePasswordAuthenticationFilter를 대체해서 추가하기 떄문에 At을 사용
////        http
////                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), refreshRedisRepository,jwtUtil), UsernamePasswordAuthenticationFilter.class);
////        http
////                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRedisRepository), LogoutFilter.class);
////
////        // 세션 사용하지 않은
////        http
////                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////        http
////                .headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin())); // H2 콘솔이 iframe으로 로드될 수 있도록 설정
////
////
////        return http.build();
////    }
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
