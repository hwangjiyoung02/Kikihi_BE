package org.jiyoung.kikihi.domain.product.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.domain.product.entity.Product;
import org.jiyoung.kikihi.domain.product.entity.ProductOption;
import org.jiyoung.kikihi.domain.product.entity.ProductTag;

import java.util.List;

@Getter
@Builder
public class ProductTagsDto {
    private List<String> tags; // 최대 5개 태그

    // 생성자
//    public static ProductTagsDto fromEntity(ProductTag productTag) {
//        return ProductTagsDto.builder()
//                .tags(productTag.getProduct().getProductTags().stream()
//                        .map(ProductTagsDto::)
//                        .toList()) // 옵션 리스트 변환
//                .build();
//    }

}
