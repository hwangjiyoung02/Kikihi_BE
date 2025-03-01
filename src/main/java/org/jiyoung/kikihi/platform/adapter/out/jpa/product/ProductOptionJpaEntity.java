package org.jiyoung.kikihi.platform.adapter.out.jpa.product;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.platform.domain.product.ProductOption;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "product_options")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_option_id")
    private Long productOptionId;

    // 직접적인 연관관계 제거하고 ID만 저장
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "color", nullable = true)
    private String color;

    @Column(name = "option", nullable = false)
    private String option;

    // from
    public static ProductOptionJpaEntity from(ProductOption option) {
        return ProductOptionJpaEntity.builder()
                .productId(option.getProductId()) // ID만 저장
                .color(option.getColor())
                .option(option.getOption())
                .build();
    }

    // toDomain
    public ProductOption toDomain() {
        return ProductOption.builder()
                .productOptionId(productOptionId)
                .productId(productId)
                .color(color)
                .option(option)
                .build();
    }
}
