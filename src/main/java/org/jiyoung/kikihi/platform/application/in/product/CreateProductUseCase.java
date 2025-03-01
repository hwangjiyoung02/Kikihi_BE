package org.jiyoung.kikihi.platform.application.in.product;

import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;

public interface CreateProductUseCase {

    /*
        판매할 상품을 등록하는 유즈케이스
     */

    /*
        상품 등록하기
        옵션과 태그를 설정해야한다.
     */
    Product createProduct(ProductRequest request);


}
