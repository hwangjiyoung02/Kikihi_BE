package org.jiyoung.kikihi.platform.application.in.category;

import org.jiyoung.kikihi.platform.domain.keyboard.category.Category;

public interface CreateCategoryUseCase {

    // 카테고리 생성하기
    Category createCategory(Category category);

}
