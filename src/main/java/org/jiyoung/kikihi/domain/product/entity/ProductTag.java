package org.jiyoung.kikihi.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.domain.product.dto.request.ProductTagDto;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "product_tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_tag_id")
    private Long productTagId;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //  CascadeType.ALL추가 안해도product가 tag add할떄 저장이 안됐음

    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @Setter // product엔티티에서 연관관계 매핑!!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
