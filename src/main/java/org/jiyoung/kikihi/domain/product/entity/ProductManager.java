package org.jiyoung.kikihi.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_manager")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_manager_id")
    private Long productManagerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "modifier_type", length = 50)
    private String modifierType;

    @Column(name = "value_type", length = 50)
    private String valueType;

    @Column(name = "applied_from")
    private LocalDateTime appliedFrom;

    @Column(name = "applied_to")
    private LocalDateTime appliedTo;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
