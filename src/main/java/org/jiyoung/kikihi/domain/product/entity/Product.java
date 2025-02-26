package org.jiyoung.kikihi.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 활성화
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String productTitle;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private Integer likeCount = 0;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductImg> productImgs= new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOption> productOptions = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductTag> productTags = new ArrayList<>();

    @CreatedDate
    @Column(name = "saved_at", nullable = false)
    private LocalDateTime savedAt;

    public void setCategory(Category category) {
        this.category = category;
        category.getProducts().add(this);
    }
}
