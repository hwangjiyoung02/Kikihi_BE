package org.jiyoung.kikihi.platform.domain.keyboard.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductOption {

    private Long id;

    private Long productId;

    private String color;
    private String switchType;
    private String layout;
    private boolean isWireless;

    private int extraPrice; // 옵션 추가 가격

    ///  생성자
    public static ProductOption of(Long productId, String color, String switchType, String layout, boolean isWireless, int extraPrice) {
        return ProductOption.builder()
                .productId(productId)
                .color(color)
                .switchType(switchType)
                .layout(layout)
                .isWireless(isWireless)
                .extraPrice(extraPrice)
                .build();
    }
}
