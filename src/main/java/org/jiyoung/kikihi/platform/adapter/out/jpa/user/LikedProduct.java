package org.jiyoung.kikihi.platform.adapter.out.jpa.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "liked_products")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class LikedProduct {

    @EmbeddedId
    private LikedProductId id;

    @Column(name = "liked_at", nullable = false)
    private LocalDateTime likedAt;

    // from

    // toDomain
}