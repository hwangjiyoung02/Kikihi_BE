package org.jiyoung.kikihi.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.domain.product.dto.request.ProductImageDto;
import org.jiyoung.kikihi.domain.product.dto.request.ProductOptionDto;
import org.jiyoung.kikihi.domain.product.dto.request.ProductTagsDto;
import org.jiyoung.kikihi.domain.product.dto.request.ProductTextDto;
import org.jiyoung.kikihi.domain.product.entity.Product;
import org.jiyoung.kikihi.domain.product.entity.ProductImg;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponseDto {
    @JsonProperty("textInfo")
    private ProductTextDto textInfo;

    @JsonProperty("imageInfo")
    private ProductImageDto imageInfo;

    @JsonProperty("optionInfo")
    private ProductOptionDto optionInfo;

    @JsonProperty("tagsInfo")
    private ProductTagsDto tagsInfo;


}
