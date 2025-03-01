package org.jiyoung.kikihi.platform.adapter.out;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.product.ProductOptionJpaEntity;
import org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.product.ProductOptionJpaRepository;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.OptionPort;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductOptionPersistenceAdapter implements OptionPort {

    /*
        옵션 JPA 구현체
     */

    private final ProductOptionJpaRepository repository;

    @Override
    public ProductOption saveOption(ProductOption productOption) {
        var entity = ProductOptionJpaEntity.from(productOption);
        return repository.save(entity)
                .toDomain();
    }

    @Override
    public Optional<ProductOption> loadOption(Long id) {
        return repository.findById(id)
                .map(ProductOptionJpaEntity::toDomain);
    }

    @Override
    public List<ProductOption> loadOptionByProductId(Long productId) {
        return repository.findByProductId(productId)
                .stream().map(ProductOptionJpaEntity::toDomain).toList();
    }

}
