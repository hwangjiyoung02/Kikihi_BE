package org.jiyoung.kikihi.platform.application.in.product;

public interface ReactionProductUseCase {
    /*
        상품에 대해서 리액션을 한다.
     */

    void addLike(Long productId, Long userId);

    void RemoveLike(Long productId, Long userId);


}
