package org.jiyoung.kikihi.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.domain.product.dto.request.ProductOptionDto;

@Entity
@Table(name = "product_options")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_option_id")
    private Long productOptionId;

    @Setter // product엔티티에서 연관관계 매핑!!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "color", nullable = true)
    private String color;

    @Column(name = "options", nullable = false)
    private String option;

    //생성자 dto-> 객체
    public static ProductOption from(ProductOptionDto optionInfo) {
        ProductOption productOption = ProductOption.builder()
                .color(optionInfo.getColor())
                .option(optionInfo.getOption())
                .build();
        return productOption;
    }


}
