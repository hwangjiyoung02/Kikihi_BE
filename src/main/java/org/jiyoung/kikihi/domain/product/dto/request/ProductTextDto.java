package org.jiyoung.kikihi.domain.product.dto.request;


import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.domain.product.entity.Product;
import org.jiyoung.kikihi.domain.product.entity.ProductOption;

import java.util.List;

@Getter
@Builder
public class ProductTextDto {
    private Long categoryId;
    private String productName;
    private String brand;
    private int productPrice;
    private String productTitle;
    private String manufacturer;
    private List<String> options;

    // 생성자
    public static ProductTextDto fromEntity(Product product) {
        return ProductTextDto.builder()
                .categoryId(product.getCategory().getCategoryId())
                .productName(product.getProductName())
                .brand(product.getBrand())
                .productPrice(product.getProductPrice())
                .productTitle(product.getProductTitle()) // productTitle 필드가 Product 엔티티에 있어야 함
                .manufacturer(product.getManufacturer())
                .options(product.getProductOptions().stream()
                        .map(ProductOption::getOption)
                        .toList()) // 옵션 리스트 변환
                .build();
    }
}