package org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionResponse {

    private String color;   // 색상
    private String switchType;
    private String layout;
    private boolean isWireless;


    // 단일 옵션 변경
    public static ProductOptionResponse from(ProductOption productOption) {
        return ProductOptionResponse.builder()
                .color(productOption.getColor())
                .switchType(productOption.getSwitchType())
                .layout(productOption.getLayout())
                .isWireless(productOption.isWireless())
                .build();
    }

    // 여러 옵션 변경
    public static List<ProductOptionResponse> from(List<ProductOption> options) {
        return options.stream().map(ProductOptionResponse::from).toList();
    }
}
