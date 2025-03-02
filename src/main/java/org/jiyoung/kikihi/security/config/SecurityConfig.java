package org.jiyoung.kikihi.security.config;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.infrastructure.redis.RefreshRepository;
import org.jiyoung.kikihi.infrastructure.oauth2.CustomOAuth2UserService;
//import org.jiyoung.kikihi.infrastructure.oauth2.handler.CustomSuccessHandler;
import org.jiyoung.kikihi.security.filter.JwtFilter;
import org.jiyoung.kikihi.security.util.JWTUtil;
import org.jiyoung.kikihi.security.filter.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    //authencationManager가 인자로 받을 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    //CustomOAuth2UserService 주입
    private final CustomOAuth2UserService customOAuth2UserService;
    //핸들러 주입
//    private final CustomSuccessHandler customSuccessHandler;
    //JWTUtil 주입
    private final JWTUtil jwtUtil;
    //refreshRepository 주입
    private final RefreshRepository refreshRepository;

    //authenticationManager를 생성하는 메서드가 있지만, @Bean이 없어서 Spring에서 인식하지 못함.
    //LoginFilter에서 authenticationManager(authenticationConfiguration)을 사용하지만, 이 메서드는 @Bean이 아니라 직접 호출할 수 없음.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return this.authenticationConfiguration.getAuthenticationManager();
    }

    //filterChainProxy ->순서가 중요!
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(Arrays.asList(
//                            "https://d1t9cmfryhhcaq.cloudfront.net",
//                            "http://localhost:9000",
//                            "http://127.0.0.1:9000"
                            "/**"
                    ));
                    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    configuration.setAllowCredentials(true);
                    configuration.addAllowedHeader("*");
                    configuration.addExposedHeader("Set-Cookie");
                    configuration.addExposedHeader("Authorization");
                    return configuration;
                }));
        http
                .csrf((auth) -> auth.disable());
        http
                .formLogin((auth) -> auth.disable());
        http
                .httpBasic((auth) -> auth.disable());
        //oauth2
//        http
//                .oauth2Login((oauth2)->oauth2
//                        .userInfoEndpoint((userInfoEndpointConfig -> userInfoEndpointConfig
//                        .userService(customOAuth2UserService)))
//                        .successHandler(customSuccessHandler)
//                        );

        // 경로별 인가 작업
        http
                .authorizeHttpRequests(((auth) -> auth.requestMatchers("/h2-console/**","/join",
                                "/login","/","/reissue","/oauth2/**","/**").permitAll()
                        .anyRequest().authenticated()));
        //필터 추가
        http
                .addFilterBefore(new JwtFilter(jwtUtil),LoginFilter.class);
//        //UsernamePasswordAuthenticationFilter를 대체해서 추가하기 떄문에 At을 사용
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil), UsernamePasswordAuthenticationFilter.class);
////        http
//                .addFilterBefore(new CustomLogoutFilter(jwtUtil,refreshRepository), LogoutFilter.class);

        // 세션 사용하지 않은
        http
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin())); // H2 콘솔이 iframe으로 로드될 수 있도록 설정


        return http.build();
    }



    //암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
