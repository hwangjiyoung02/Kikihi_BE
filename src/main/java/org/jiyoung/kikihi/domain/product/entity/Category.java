package org.jiyoung.kikihi.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")  // Product에서 category 필드로 관계를 맺고 있음
    @Builder.Default
    private List<Product> products = new ArrayList<>();;
}

