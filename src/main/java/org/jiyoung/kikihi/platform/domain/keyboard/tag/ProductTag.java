package org.jiyoung.kikihi.platform.domain.keyboard.tag;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTag {

    private Long id;

    private Long tagId;

    private Long productId;

    /// 생성자
    public static ProductTag of(Long tagId, Long productId) {
        return ProductTag.builder()
                .tagId(tagId)
                .productId(productId)
                .build();

    }
}
