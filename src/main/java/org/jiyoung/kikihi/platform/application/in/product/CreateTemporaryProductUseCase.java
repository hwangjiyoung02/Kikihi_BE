package org.jiyoung.kikihi.platform.application.in.product;

import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;

public interface CreateTemporaryProductUseCase {

    /*
        판매할 상품을 임시 등록하는 유즈케이스
     */

    // 상품 임시저장 등록하기
    void saveTemporaryProduct(ProductRequest request);

    // 임시저장한 상품 목록 가져오기
    Product getTemporaryProduct(Long userId);

}
