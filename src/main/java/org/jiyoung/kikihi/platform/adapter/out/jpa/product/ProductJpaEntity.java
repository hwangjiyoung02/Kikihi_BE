package org.jiyoung.kikihi.platform.adapter.out.jpa.product;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.platform.adapter.out.jpa.BaseEntity;
import org.jiyoung.kikihi.platform.domain.product.Product;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@Builder
@Table(name = "product")
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Document(indexName = "product") // Elasticsearch에서 사용할 인덱스
public class ProductJpaEntity extends BaseEntity {

    // JPA용 ID 필드 (기본키)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer productPrice = 0;

    @Embedded
    private ProductSnippetJpaEntity snippet;

    @Embedded
    private ProductStatisticsJpaEntity statistics;

    @org.springframework.data.annotation.Id
    private String elasticsearchId;

    // from
    public static ProductJpaEntity from(Product product) {
        return ProductJpaEntity.builder()
                .productName(product.getProductName())
                .description(product.getDescription())
                .categoryId(product.getProductId())
                .productPrice(product.getProductPrice())
                .snippet(ProductSnippetJpaEntity.from(product.getSnippet()))
                .statistics(ProductStatisticsJpaEntity.from(product.getStatistics()))
                .build();
    }

    // toDomain
    public Product toDomain(){
        return Product.builder()
                .productId(productId)
                .productName(productName)
                .categoryId(categoryId)
                .description(description)
                .snippet(snippet.toDomain())
                .statistics(statistics.toDomain())
                .build();
    }
}
