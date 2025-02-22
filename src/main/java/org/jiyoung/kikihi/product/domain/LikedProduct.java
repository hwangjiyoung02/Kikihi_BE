package org.jiyoung.kikihi.product.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "liked_products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LikedProduct {
    @EmbeddedId
    private LikedProductId id;

    @Column(name = "liked_at", nullable = false)
    private LocalDateTime likedAt;
}