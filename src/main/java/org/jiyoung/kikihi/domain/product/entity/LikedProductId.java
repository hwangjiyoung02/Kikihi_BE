package org.jiyoung.kikihi.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikedProductId implements Serializable {
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "product_id")
    private Long productId;
}