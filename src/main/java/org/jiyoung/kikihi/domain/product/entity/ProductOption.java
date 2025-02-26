package org.jiyoung.kikihi.domain.product.entity;

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

    @Column(name = "color", nullable = true)
    private String color;

    @Column(name = "options", nullable = false)
    private String option;




}
