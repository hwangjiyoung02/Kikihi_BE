package org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product;

import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductSnippet;

@Getter
@Builder
@Data
public class ProductBasicResponse {

    private String categoryId;

    private String productName;

    private String brand;

    private int productPrice;

    private String productTitle;

    private String manufacturer;

    /// from
    public static ProductBasicResponse from(Product product) {
        ProductSnippet snippet = product.getSnippet();

        return ProductBasicResponse.builder()
                .categoryId(product.getCategoryCode())
                .productName(product.getProductName())
                .brand(snippet.getBrand())
                .productPrice(product.getProductPrice())
                .productTitle(snippet.getProductTitle()) // productTitle 필드가 Product 엔티티에 있어야 함
                .manufacturer(snippet.getManufacturer())
                .build();
    }


}
