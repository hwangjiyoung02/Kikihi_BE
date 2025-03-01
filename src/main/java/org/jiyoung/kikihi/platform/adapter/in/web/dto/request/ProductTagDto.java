package org.jiyoung.kikihi.platform.adapter.in.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.out.jpa.product.ProductTag;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTagDto {

    @JsonProperty("tags")
    private List<String> tags; // 최대 5개 태그

    public static ProductTagDto from(ProductTag productTag) {
        return ProductTagDto.builder()
                .tags((List<String>) productTag.getTag())
                .build();
    }

}
