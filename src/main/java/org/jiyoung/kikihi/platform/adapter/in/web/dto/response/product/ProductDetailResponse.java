package org.jiyoung.kikihi.platform.adapter.in.web.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProductDetailResponse {

    private ProductBasicResponse basic;

    private List<ProductOptionResponse> option;

    private List<ProductTagResponse> tag;

    public static ProductDetailResponse from(Product product, List<ProductOption> options) {

        return ProductDetailResponse.builder()
                .basic(ProductBasicResponse.from(product))
                .option(ProductOptionResponse.from(options))
                .build();
    }

    
}
