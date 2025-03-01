package org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.product;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductSnippet;


@Getter
@Builder
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductSnippetJpaEntity {

    private String productTitle;

    private String brand;

    private String manufacturer;

    public static ProductSnippetJpaEntity from(ProductSnippet domain) {
        return ProductSnippetJpaEntity.builder()
                .productTitle(domain.getProductTitle())
                .brand(domain.getBrand())
                .manufacturer(domain.getManufacturer())
                .build();
    }

    public ProductSnippet toDomain() {
        return ProductSnippet.builder()
                .productTitle(productTitle)
                .brand(brand)
                .manufacturer(manufacturer)
                .build();
    }


}
