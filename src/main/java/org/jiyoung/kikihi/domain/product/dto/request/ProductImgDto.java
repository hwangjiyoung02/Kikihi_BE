package org.jiyoung.kikihi.domain.product.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jiyoung.kikihi.domain.product.entity.ProductImg;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImgDto {
    private String thumbnailImage;
    private List<String> mainImages;
    private List<String> descriptionImages;
    private String descriptionHtml;  // HTML 설명

    public static ProductImgDto from(ProductImg productImg) {
        return ProductImgDto.builder()
                .thumbnailImage(productImg.getThumbnailImg())
                .mainImages(productImg.getMainImgs())
                .descriptionImages(productImg.getDescriptionImgs())
                .descriptionHtml(productImg.getDescriptionHtml())
                .build();
    }

}