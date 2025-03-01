package org.jiyoung.kikihi.platform.adapter.in.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTemporarySaveDto {
    @JsonProperty("textInfo")
    private ProductTextDto textInfo;

    @JsonProperty("imageInfo")
    private ProductImgDto imageInfo;

    @JsonProperty("optionInfo")
    private ProductOptionDto optionInfo;

    @JsonProperty("tagsInfo")
    private ProductTagDto tagsInfo;
}

