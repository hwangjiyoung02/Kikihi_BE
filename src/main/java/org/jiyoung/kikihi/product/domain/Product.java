package org.jiyoung.kikihi.product.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)  // nullable=true 추가
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "saved_at", nullable = false)
    private LocalDateTime savedAt;
}
