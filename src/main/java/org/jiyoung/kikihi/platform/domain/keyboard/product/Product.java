package org.jiyoung.kikihi.platform.domain.keyboard.product;

import lombok.*;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductBasicRequest;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.domain.BaseDomain;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product extends BaseDomain {

    private Long productId;

    private String productName;

    private String description;

    private String categoryCode;

    private Integer productPrice = 0;

    private ProductSnippet snippet;

    private ProductStatistics statistics;

    /// 생성자
    public static Product from(String name, String description, String categoryCode, Integer price, ProductSnippet snippet, ProductStatistics statistics) {
        return Product.builder()
                .productName(name)
                .description(description)
                .categoryCode(categoryCode)
                .productPrice(price)
                .snippet(snippet)
                .statistics(statistics)
                .build();
    }

    /// Request
    public static Product from(ProductRequest request) {

        // 기본정보로 생성
        ProductBasicRequest basic = request.getBasic();


        ProductSnippet snippet = ProductSnippet.of(basic.getProductTitle(), basic.getBrand(), basic.getManufacturer());
        ProductStatistics statistics = ProductStatistics.of(0, 0, 0);

        return Product.builder()
                .productName(basic.getProductName())
                .description(basic.getDescription())
                .categoryCode(basic.getCategoryCode())
                .productPrice(basic.getPrice())
                .snippet(snippet)
                .statistics(statistics)
                .build();
    }

    ///  비즈니스 로직

}
