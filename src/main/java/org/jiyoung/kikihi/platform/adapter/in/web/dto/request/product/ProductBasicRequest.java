package org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product;

import lombok.Data;

@Data
public class ProductBasicRequest {

    // 상품 기본 정보들 등록
    private String categoryCode;
    private String productName;
    private String brand;
    private String productTitle;
    private String manufacturer;
    private String description;
    private int price;

}
