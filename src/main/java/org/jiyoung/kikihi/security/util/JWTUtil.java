package org.jiyoung.kikihi.security.util;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;// 롬복이 아니라 bean으로 주입받아야 함
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

//0.12.3 버전(가장최신)
@Component
public class JWTUtil {

    private SecretKey secretKey;

    //생성자 방식으로 호출될때 secret값을 받아서 secretKey를 생성
    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getEmail(String token) {
    //token을 파싱해서 email을 리턴(토큰이 우리 서버에서 생성된건지,우리키랑 맞는지 확인)
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
                .getPayload().get("email", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get("role", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    // 시그니처가 달라도 상관없음
    public String createJwt(String email,String role, Long expiredMs) {

        return Jwts.builder()
                .claim("email", email)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }


}
