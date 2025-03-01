package org.jiyoung.kikihi.platform.adapter.out.jpa.product;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.ProductTextDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
@Document(indexName = "product") // Elasticsearch에서 사용할 인덱스
public class Product {

    // JPA용 ID 필드 (기본키)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long productId; // JPA에서 사용될 ID

    // Elasticsearch용 ID 필드 (Elasticsearch ID로만 사용)
    @org.springframework.data.annotation.Id // 이 ID는 Elasticsearch용으로만 사용
    private String elasticsearchId;  // Elasticsearch에서 사용하는 ID 필드

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String productTitle;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer productPrice = 0;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer likeCount = 0;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProductImg> productImgs = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProductOption> productOptions = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProductTag> productTags = new ArrayList<>();

    @CreatedDate
    @Column(name = "saved_at", nullable = false)
    private LocalDateTime savedAt;

    // 연관관계 메서드
    public void setCategory(Category category) {
        this.category = category;
        category.getProducts().add(this);
    }

    public static Product from(ProductTextDto productTextDto) {
        return Product.builder()
                .productName(productTextDto.getProductName())
                .manufacturer(productTextDto.getManufacturer())
                .productTitle(productTextDto.getProductTitle())
                .productPrice(productTextDto.getProductPrice())
                .brand(productTextDto.getBrand())
                .likeCount(0) // 기본값 추가
                .productPrice(0)
                .build();
    }

    // 연관관계 메서드
    public void addProductOption(ProductOption productOption) {
        productOptions.add(productOption);
        productOption.setProduct(this);
    }

    public void addProductImg(ProductImg productImg) {
        productImgs.add(productImg);
        productImg.setProduct(this);
    }

    public void addProductTag(ProductTag productTag) {
        productTags.add(productTag);
        productTag.setProduct(this);
    }

    public void addTags(List<Tag> tags) {
        for (Tag tag : tags) {
            ProductTag productTag = ProductTag.builder()
                    .product(this)  // 현재 Product 설정
                    .tag(tag)       // 해당 Tag 설정
                    .build();
            this.addProductTag(productTag); // 연관관계 설정
        }
    }
}
