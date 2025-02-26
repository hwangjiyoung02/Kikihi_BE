package org.jiyoung.kikihi.domain.product.dto.request;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductImageDto {
    private String thumbnailImage;
    private List<String> mainImages;
    private List<String> descriptionImages;
    private String descriptionHtml;  // HTML 설명

}