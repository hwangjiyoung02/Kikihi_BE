package org.jiyoung.kikihi.platform.adapter.out.jpa.product;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.jiyoung.kikihi.platform.domain.product.ProductStatistics;

@Getter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductStatisticsJpaEntity {

    private Integer likeCount = 0;
    private Integer commentCount = 0;
    private Integer viewCount = 0;

    /// from
    public static ProductStatisticsJpaEntity from(ProductStatistics domain) {
        return ProductStatisticsJpaEntity.builder()
                .likeCount(domain.getLikeCount())
                .commentCount(domain.getCommentCount())
                .viewCount(domain.getViewCount())
                .build();
    }

    ///  toDomain
    public ProductStatistics toDomain(){
        return ProductStatistics.builder()
                .likeCount(likeCount)
                .commentCount(commentCount)
                .viewCount(viewCount)
                .build();
    }

}
