package org.jiyoung.kikihi.platform.adapter.out.jpa.user;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor

public class LikedProductId implements Serializable {

    @Column(name = "User_id")
    private Long UserId;

    @Column(name = "product_id")
    private Long productId;

    // from

    // toDomain

}