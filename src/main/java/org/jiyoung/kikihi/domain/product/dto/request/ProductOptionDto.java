package org.jiyoung.kikihi.domain.product.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductOptionDto {
    private String color;   // 색상
    private String option;  // 옵션 (예: 키스위치 타입 등)
}
