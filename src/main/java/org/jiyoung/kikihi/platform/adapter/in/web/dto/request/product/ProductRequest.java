package org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product;

import lombok.*;

import java.util.List;

@Data
public class ProductRequest {

    // 상품 기본 정보들 등록
    private ProductBasicRequest basic;

    // 가능한 옵션 여러개 생성
    private List<ProductOptionRequest> options;

    // 최대 5개의 태그 선택
    private List<ProductTagRequest> tags;


}
