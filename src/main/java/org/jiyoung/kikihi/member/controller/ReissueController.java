package org.jiyoung.kikihi.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.member.service.ReissueService;
import org.jiyoung.kikihi.security.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
// 위두개를 합친게 @RestController
@RequiredArgsConstructor
public class ReissueController {
    private final JWTUtil jwtUtil;
    private final ReissueService reissueService;


    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        return reissueService.reissue(request, response);
    }


}
