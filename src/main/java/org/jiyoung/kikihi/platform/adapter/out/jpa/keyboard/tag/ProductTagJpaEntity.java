package org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.tag;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.tag.ProductTag;

@Entity
@Table(name = "product_tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductTagJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_tag_id")
    private Long productTagId;


    @Column(name = "tag_id", nullable = false)
    private Long tagId;


    @Column(name = "product_id", nullable = false)
    private Long productId;

    // from
    public static ProductTagJpaEntity from(ProductTagJpaEntity entity) {
        return ProductTagJpaEntity.builder()
                .productId(entity.getProductId())
                .tagId(entity.getTagId())
                .build();
    }

    // toDomain
    public ProductTag toDomain() {
        return ProductTag.builder()
                .id(productTagId)
                .productId(productId)
                .tagId(tagId)
                .build();
    }

}
