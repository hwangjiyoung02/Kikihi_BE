package org.jiyoung.kikihi.member.service;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import org.jiyoung.kikihi.member.domain.RefreshEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.redis.RefreshRepository;
import org.jiyoung.kikihi.security.util.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReissueService {
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        // 쿠키에서 refresh 토큰 가져오기
        String refresh = null;

        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("refresh")){
                refresh=cookie.getValue();
            }
        }
        if (refresh == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("refresh token 없음");
        }


        // refresh 토큰 expired check
        try{
            jwtUtil.isExpired(refresh);
        }catch (ExpiredJwtException e){
            return new ResponseEntity<>("refresh token 만료", HttpStatus.UNAUTHORIZED);
        }

        // 토큰이 refresh인지 확인 (발급시 payload에 category를 넣어서 명시)
        String category=jwtUtil.getCategory(refresh);
        if(!category.equals("refresh")){
            return new ResponseEntity<>("invalid refresh token", HttpStatus.UNAUTHORIZED);
        }
//        // 4. DB에서 refresh 토큰 확인
//        Optional<RefreshEntity> refreshEntity = refreshRepository.findByRefresh(refresh);
//        if (refreshEntity.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid refresh token");
//        }

        String email = jwtUtil.getEmail(refresh);

        // Access/Refresh 토큰 발급
        String newAccess = jwtUtil.createJwt("access", email, 600000L);
        //7. refresh 토큰 만료시간 24시간으로 설정
        String newRefresh = jwtUtil.createJwt("refresh", email, 84000000L);

        // 8. DB 업데이트 (기존 refresh 삭제 후 새로 저장)
        refreshRepository.deleteByRefresh(refresh);
        addRefreshEntity(email,refresh,84000000L);        //8.refresh 토큰 저장


        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh)); // 8. 새 토큰을 Response에 담아 반환


        return ResponseEntity.ok().build();
    }

    // 1. 쿠키에서 Refresh Token 가져오기
    private String extractRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // 2. Refresh 토큰 검증
    private boolean validateRefreshToken(String refresh) {
        try {
            jwtUtil.isExpired(refresh);
            return "refresh".equals(jwtUtil.getCategory(refresh));
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    // 3. Refresh 토큰 저장
    private void saveRefreshToken(String username, String refresh, Long expiredMs) {
            RefreshEntity entity =RefreshEntity.builder()
                .refresh(refresh)
                .username(username)
                .expiration(new Date(System.currentTimeMillis() + expiredMs).toString())
                .build();
        refreshRepository.save(entity);
    }

    // 4. Refresh 쿠키 생성
    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    //8.refresh 토큰저장
    private void addRefreshEntity(String username,String refresh,Long expiredMs){
        Date date=new Date(System.currentTimeMillis()+expiredMs);
        RefreshEntity refreshEntity=new RefreshEntity();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);

    }
}
