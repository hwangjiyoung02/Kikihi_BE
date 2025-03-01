package org.jiyoung.kikihi.platform.application.out.keyboard.category;

import org.jiyoung.kikihi.platform.domain.keyboard.category.Category;

import java.util.Optional;

public interface CategoryPort {

    // 카테고리 저장
    Category createCategory(Category category);

    // 카테고리 가져오기
    Optional<Category> loadCategoryById(Long id);

    // 카테고리 코드 가져오기
    Optional<Category> loadCategoryByCode(String code);

    // 카테고리가 존재하는지 체크
    boolean existsCategory(String code);

    // 카테고리 삭제
    void deleteCategory(Long id);

}
