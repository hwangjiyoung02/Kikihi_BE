package org.jiyoung.kikihi.platform.adapter.in.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.out.jpa.product.Product;
import org.jiyoung.kikihi.platform.adapter.out.jpa.product.ProductOption;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTextDto {

    @JsonProperty("categoryId")
    private Long categoryId;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("productPrice")
    private int productPrice;

    @JsonProperty("productTitle")
    private String productTitle;

    @JsonProperty("manufacturer")
    private String manufacturer;

    @JsonProperty("options")
    private List<String> options;

    // 생성자
    public static ProductTextDto from(Product product) {
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
