package org.jiyoung.kikihi.platform.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductOption {

    private Long productOptionId;

    private Long productId;

    private String color;

    private String option;

    ///  생성자
    public static ProductOption of(Long productId, String color, String option) {
        return ProductOption.builder()
                .productId(productId)
                .color(color)
                .option(option)
                .build();
    }
}
