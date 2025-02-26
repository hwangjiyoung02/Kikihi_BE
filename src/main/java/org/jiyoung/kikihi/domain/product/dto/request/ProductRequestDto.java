package org.jiyoung.kikihi.domain.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String category;
    private String productName;
    private String brand;
    private String productTitle;
    private String manufacturer;
    private List<String> options; // 색상, 키스위치
    private String thumbnailImage; // 정사각형 이미지
    private List<String> detailImages; // 상품 상세 이미지
    private List<String> additionalImages; // 기타 이미지
    private List<String> productDescriptionImages; // 상품 상세 설명 이미지
    private List<String> tags; // 최대 5개 태그

}
