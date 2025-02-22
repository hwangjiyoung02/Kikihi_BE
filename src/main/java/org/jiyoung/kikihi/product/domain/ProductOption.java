package org.jiyoung.kikihi.product.domain;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "option_name", nullable = false)
    private String optionName;

    @Column(name = "option_value", nullable = false)
    private String optionValue;

    @Column(name = "option_quantity", nullable = false)
    private Integer optionQuantity;
}
