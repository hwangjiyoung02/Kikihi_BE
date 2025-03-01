package org.jiyoung.kikihi.platform.adapter.out;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.category.CategoryJpaEntity;
import org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.category.CategoryJpaRepository;
import org.jiyoung.kikihi.platform.application.out.keyboard.category.CategoryPort;
import org.jiyoung.kikihi.platform.domain.keyboard.category.Category;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryPort {

    /*
        카테고리 JPA 구현체
     */

    private final CategoryJpaRepository repository;

    @Override
    public Category createCategory(Category category) {
        var entity = CategoryJpaEntity.from(category);
        return repository.save(entity)
                .toDomain();
    }

    @Override
    public Optional<Category> loadCategoryById(Long id) {
        return repository.findById(id)
                .map(CategoryJpaEntity::toDomain);
    }

    @Override
    public Optional<Category> loadCategoryByCode(String code) {
        return repository.findByCode(code)
                .map(CategoryJpaEntity::toDomain);
    }

    @Override
    public boolean existsCategory(String code) {
        return repository.existsByCode(code);
    }

    @Override
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }


}
