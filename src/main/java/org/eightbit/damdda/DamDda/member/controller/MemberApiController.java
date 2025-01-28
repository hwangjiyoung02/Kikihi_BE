package org.eightbit.damdda.DamDda.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.eightbit.damdda.DamDda.common.ApiResponse;
import org.eightbit.damdda.DamDda.member.service.MemberService;
import org.eightbit.damdda.DamDda.member.service.request.AddUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원", description = "회원 관련 API")

public class MemberApiController {

    private final MemberService memberService;

    // 회원가입
    @Operation(summary = "회원가입", description = "사용자가 회원가입을 할 수 있는 API")
    @PostMapping("/join")
    public ApiResponse<String> join(@RequestBody AddUserRequest request) {
        // 실제 회원가입 로직
        memberService.joinProcess(request);
        return ApiResponse.ok("회원가입성공");
    }

    @GetMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return ApiResponse.ok("로그아웃 성공");
    }
}