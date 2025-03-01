package org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductSnippet;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProductListResponse {

    private String categoryName;

    private String productName;

    private String brand;

    private int productPrice;

    private String productTitle;

    private String manufacturer;

    public static ProductListResponse from(Product entity) {
        ProductSnippet snippet = entity.getSnippet();

        return ProductListResponse.builder()
                .categoryName(entity.getCategoryCode())
                .productName(entity.getProductName())
                .productTitle(snippet.getProductTitle())
                .productPrice(entity.getProductPrice())
                .brand(snippet.getBrand())
                .manufacturer(snippet.getManufacturer())
                .build();
    }

    public static List<ProductListResponse> from(List<Product> entities) {
       return entities.stream().map(ProductListResponse::from).toList();
    }

}
