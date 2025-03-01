package org.jiyoung.kikihi.platform.application.in.category;

import org.jiyoung.kikihi.platform.domain.keyboard.category.Category;

import java.util.Optional;

public interface GetCategoryUseCase {

    /*
        카테고리 가져오기
     */

    // 기존에 존재하는 카테고리 연결하기
    Optional<Category> findCategoryByCode(String code);

}
