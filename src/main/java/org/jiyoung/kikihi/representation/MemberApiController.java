package org.jiyoung.kikihi.representation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.domain.common.response.ApiResponse;
import org.jiyoung.kikihi.domain.user.service.MemberService;
import org.jiyoung.kikihi.domain.user.dto.JwtUserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원", description = "회원 관련 API")

public class MemberApiController {

    private final MemberService memberService;

    // 메인
    @Operation(summary = "메인", description = "메인 API")
    @PostMapping("/")
    public ApiResponse<String> mainPage() {
        //세션에 저장되어있는 아이디 확인가능
        String name=SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("name: "+name);
        return ApiResponse.ok("메인 페이지 진입 성공");
    }

    // 회원가입
    @Operation(summary = "회원가입", description = "사용자가 회원가입을 할 수 있는 API")
    @PostMapping("/join")
    public ApiResponse<String> join(@RequestBody JwtUserDTO request) {
        // 실제 회원가입 로직
        memberService.joinProcess(request);
        return ApiResponse.ok("회원가입성공");
    }

    //로그인
//    @Operation(summary = "로그인", description = "사용자가 로그인을 할 수 있는 API")
//    @PostMapping("/login")
//    public ApiResponse<String> login(@RequestBody AddUserRequest request) {
//        // 실제 회원가입 로직
//        memberService.joinProcess(request);
//        return ApiResponse.ok("회원가입성공");
//    }


    @GetMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return ApiResponse.ok("로그아웃 성공");
    }
}