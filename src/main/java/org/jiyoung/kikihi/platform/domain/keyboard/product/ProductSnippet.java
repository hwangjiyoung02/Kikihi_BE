package org.jiyoung.kikihi.platform.domain.keyboard.product;

import lombok.*;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductSnippet {

    private String productTitle;

    private String brand;

    private String manufacturer;

    ///  생성자
    public static ProductSnippet of(String productTitle, String brand, String manufacturer) {
        return ProductSnippet.builder()
                .productTitle(productTitle)
                .brand(brand)
                .manufacturer(manufacturer)
                .build();
    }

}
