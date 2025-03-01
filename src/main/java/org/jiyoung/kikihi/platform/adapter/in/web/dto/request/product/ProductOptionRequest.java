package org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionRequest {

    private String color;   // 색상
    private String switchType; // 축 설정
    private String layout; // 텐키리스..
    private boolean isWireless; // 무선,유선
    private int extraPrice; // 옵션 추가 가격

}
