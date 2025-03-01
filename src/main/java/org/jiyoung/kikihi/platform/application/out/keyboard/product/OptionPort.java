package org.jiyoung.kikihi.platform.application.out.keyboard.product;

import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;

import java.util.List;
import java.util.Optional;

public interface OptionPort {

    // 옵션 저장하기
    ProductOption saveOption(ProductOption productOption);

    // 옵션 상세보기
    Optional<ProductOption> loadOption(Long id);

    // 아이템에 해당하는 옵션 목록 가져오기
    List<ProductOption> loadOptionByProductId(Long productId);
}
