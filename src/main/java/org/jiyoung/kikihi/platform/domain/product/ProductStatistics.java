package org.jiyoung.kikihi.platform.domain.product;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductStatistics {

    private Integer likeCount = 0;
    private Integer commentCount = 0;
    private Integer viewCount = 0;

    /// 생성자
    public static ProductStatistics of(int likeCount, int commentCount, int viewCount) {
        return ProductStatistics.builder()
                .likeCount(likeCount)
                .commentCount(commentCount)
                .viewCount(viewCount)
                .build();
    }
}
