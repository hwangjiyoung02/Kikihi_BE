package org.jiyoung.kikihi.platform.domain.product;

import lombok.*;
import org.jiyoung.kikihi.platform.adapter.out.jpa.product.tag.ProductTag;
import org.jiyoung.kikihi.platform.adapter.out.jpa.product.tag.Tag;
import org.jiyoung.kikihi.platform.domain.BaseDomain;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product extends BaseDomain {

    private Long productId;

    private String productName;

    private String description;

    private Long categoryId;

    private Integer productPrice = 0;

    private ProductSnippet snippet;

    private ProductStatistics statistics;

    /// 생성자
    public static Product from(String name, String description, Long categoryId, Integer price, ProductSnippet snippet, ProductStatistics statistics) {
        return Product.builder()
                .productName(name)
                .description(description)
                .categoryId(categoryId)
                .productPrice(price)
                .snippet(snippet)
                .statistics(statistics)
                .build();
    }

    ///  비즈니스 로직

}
