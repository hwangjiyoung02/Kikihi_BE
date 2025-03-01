package org.jiyoung.kikihi.platform.application.out.keyboard.product;

import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;

public interface SaveProductPort {

    // 상품 등록하기
    Product createProduct(Product product);
}
