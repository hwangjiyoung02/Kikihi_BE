package org.jiyoung.kikihi.platform.application.service.keyboard;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.CategoryRequest;
import org.jiyoung.kikihi.platform.application.in.category.CreateCategoryUseCase;
import org.jiyoung.kikihi.platform.application.in.category.GetCategoryUseCase;
import org.jiyoung.kikihi.platform.application.out.keyboard.category.CategoryPort;
import org.jiyoung.kikihi.platform.domain.keyboard.category.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements CreateCategoryUseCase, GetCategoryUseCase {

    private final CategoryPort categoryPort;

    @Override
    public Category createCategory(CategoryRequest request) {
        Category parent = null;

        if (request.getParentId() != null) {
            parent = categoryPort.loadCategoryById(request.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("해당하는 부모가 없습니다."));
        }

        Category category = Category.of(parent, request.getName(), request.getCode(), request.getDescription());
        return categoryPort.createCategory(category);
    }


    @Override
    public Optional<Category> findCategoryByCode(String code) {
        return categoryPort.loadCategoryByCode(code);
    }


}
