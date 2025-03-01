package org.jiyoung.kikihi.platform.application.in.product;

import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;

import java.util.*;

public interface GetProductOptionUseCase {
    /*
        사용자가 키보드의 옵션 목록을 볼 수 있도록 하는 유즈케이스이다.
     */

    // 상품에 대한 상세 정보 가져오기
    Optional<ProductOption> getProductOption(Long optionId);

    // 상품에 대한 옵션 목록 제공
    List<ProductOption> getProductOptions(Long productId);


}
