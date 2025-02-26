package org.jiyoung.kikihi.domain.product.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jiyoung.kikihi.domain.product.dto.request.ProductImageDto;
import org.jiyoung.kikihi.domain.product.dto.request.ProductOptionDto;
import org.jiyoung.kikihi.domain.product.dto.request.ProductTagsDto;
import org.jiyoung.kikihi.domain.product.dto.request.ProductTextDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTemporarySaveDto {
    @JsonProperty("textInfo")
    private ProductTextDto textInfo;

    @JsonProperty("imageInfo")
    private ProductImageDto imageInfo;

    @JsonProperty("optionInfo")
    private ProductOptionDto optionInfo;

    @JsonProperty("tagsInfo")
    private ProductTagsDto tagsInfo;
}

