package org.jiyoung.kikihi.platform.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.common.response.ApiResponse;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.CategoryRequest;
import org.jiyoung.kikihi.platform.application.in.category.CreateCategoryUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryApi {

    private final CreateCategoryUseCase createService;

    @PostMapping
    public ApiResponse<String> create(@RequestBody CategoryRequest request) {
        createService.createCategory(request);
        return ApiResponse.created("생성되었습니다.");
    }
}
