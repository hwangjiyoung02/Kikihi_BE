package org.jiyoung.kikihi.platform.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.product.ProductJpaEntity;
import org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.product.ProductJpaRepository;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.SaveProductPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.DeleteProductPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.LoadProductPort;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements LoadProductPort, SaveProductPort, DeleteProductPort {

    /*
        Redis와 JPA 복합 사용 Adapter
     */

    private final ProductJpaRepository repository;

    @Override
    public Product createProduct(Product product) {
        return repository.save(ProductJpaEntity.from(product))
                .toDomain();
    }

    @Override
    public Optional<Product> loadProductById(Long productId) {
        return repository.findById(productId)
                .map(ProductJpaEntity::toDomain);
    }

    @Override
    public Page<Product> loadProducts(Pageable pageable) {
        return repository.findAllByOrderByCreatedAtDesc(pageable)
                .map(ProductJpaEntity::toDomain);
    }

    @Override
    public Page<Product> loadProductsByLike(Pageable pageable) {
        return repository.findAllByOrderByStatistics_LikeCountDesc(pageable)
                .map(ProductJpaEntity::toDomain);
    }

    @Override
    public Page<Product> loadProductsByCondition(Pageable pageable, String productTitle, Double minPrice, Double maxPrice) {
        Specification<ProductJpaEntity> condition = of(productTitle, minPrice, maxPrice);
        return repository.findAll(condition, pageable)
                .map(ProductJpaEntity::toDomain);
    }

    @Override
    public void deleteProduct(Long productId) {
        repository.deleteById(productId);
    }

    // 동적 쿼리 생성 (필터링 조건을 Specification으로 처리)
    private Specification<ProductJpaEntity> of(String productTitle, Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (productTitle != null && !productTitle.isEmpty()) {
                predicates.add((Predicate) criteriaBuilder.like(root.get("productTitle"), "%" + productTitle + "%"));
            }

            if (minPrice != null) {
                predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add((Predicate) criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return (jakarta.persistence.criteria.Predicate) predicates;
        };
    }
}
