package org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.category;

import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.category.Category;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // 부모가 없는 최상위 카테고리 가능
    private CategoryJpaEntity parent;

    @Column(name = "name", nullable = false)
    private String name;

    private String code;

    private String description;


    // from
    public static CategoryJpaEntity from(Category category) {
        return CategoryJpaEntity.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .parent(category.getParent() != null ? CategoryJpaEntity.from(category.getParent()) : null)
                .code(category.getCode())
                .description(category.getDescription())
                .build();
    }

    // toDomain (CategoryJpaEntity -> Category 변환)
    public Category toDomain() {
        return Category.builder()
                .categoryId(categoryId)
                .name(name)
                .parent(parent != null ? parent.toDomain() : null)
                .code(code)
                .description(description)
                .build();
    }
}
