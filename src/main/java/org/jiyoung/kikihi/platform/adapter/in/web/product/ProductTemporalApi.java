package org.jiyoung.kikihi.platform.adapter.in.web.product;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jiyoung.kikihi.common.response.ApiResponse;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.application.in.product.CreateTemporaryProductUseCase;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductTemporalApi {

    private final CreateTemporaryProductUseCase temporaryService;

    // 임시 저장 요청
    @PostMapping("/temporary/save")
    public ApiResponse<String> saveTemporaryProduct(@RequestBody ProductRequest request) {

        temporaryService.saveTemporaryProduct(request);
        return ApiResponse.ok("상품이 임시 저장되었습니다.");
    }

    /*
        상품 등록 -> admin에서 해야함
        관리자 혹은 멤버가 상품을 추가한다.권한은 나중에 설정할거다
        multipartfile에는 상품 썸네일을 받아서 저장한다.
        등록한 사람도 저장한다.
     */

    @PostMapping("/submit")
    public ApiResponse<String> submitTemporaryProduct(HttpSession session) {

        session.removeAttribute("tempProductData"); // 세션에서 임시 데이터 제거

        return ApiResponse.ok("Product saved successfully");
    }
}
