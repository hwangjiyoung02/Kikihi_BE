//package org.jiyoung.kikihi.security.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry corsRegistry){
//        corsRegistry.addMapping("/**")
//                .exposedHeaders("Set-Cookie")
//                .allowedOrigins("https://d1t9cmfryhhcaq.cloudfront.net",
//                        "http://localhost:9000",
//                        "http://127.0.0.1:9000"
//                )//테스트 용
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowCredentials(true);
//    }
//}
