package org.jiyoung.kikihi.platform.adapter.out.jpa.product;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.ProductImgDto;

import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_imgs")
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_id")
    private Long productImgId;

    @Setter // product엔티티에서 연관관계 매핑!!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 썸네일 이미지 URL
    @Column(name = "thumbnail_img", nullable = false)
    private String thumbnailImg;

    // 상세 이미지 URL 리스트
    @ElementCollection
    @CollectionTable(name = "main_imgs", joinColumns = @JoinColumn(name = "product_img_id"))
    @Column(name = "main_imgs", nullable = false)
    private List<String> mainImgs;

    // 상품 설명 이미지 URL 리스트
    @ElementCollection
    @CollectionTable(name = "description_imgs", joinColumns = @JoinColumn(name = "product_img_id"))
    @Column(name = "description_imgs", nullable = false)
    private List<String> descriptionImgs;

    @Lob  // HTML 저장을 위해 추가
    @Column(columnDefinition = "TEXT")
    private String descriptionHtml;

    public static ProductImg from(ProductImgDto productImgDto) {
        ProductImg productImg = ProductImg.builder()
                .thumbnailImg(productImgDto.getThumbnailImage())
                .mainImgs(productImgDto.getMainImages())
                .descriptionImgs(productImgDto.getDescriptionImages())
                .descriptionHtml(productImgDto.getDescriptionHtml())
                .build();
        return productImg;
    }
}