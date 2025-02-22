package org.jiyoung.kikihi.product.domain;

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