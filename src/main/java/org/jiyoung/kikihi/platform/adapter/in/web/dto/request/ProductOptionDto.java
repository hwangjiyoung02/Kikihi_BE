package org.jiyoung.kikihi.platform.adapter.in.web.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.out.jpa.product.Product;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionDto {
    @JsonProperty("color")
    private String color;   // 색상

    @JsonProperty("option")
    private String option;  // 옵션 (예: 키스위치 타입 등)

    public static ProductOptionDto from(Product product) {
        return ProductOptionDto.builder()
                .color(product.getProductOptions().get(0).getColor())
                .option(product.getProductOptions().get(0).getOption())
                .build();
    }
}
