package org.jiyoung.kikihi.platform.application.service.keyboard;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.application.in.product.GetProductUseCase;
import org.jiyoung.kikihi.platform.application.in.product.ReactionProductUseCase;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.DeleteProductPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.LoadProductPort;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductLoadService implements GetProductUseCase, ReactionProductUseCase {

    private final LoadProductPort loadPort;
    private final DeleteProductPort deletePort;

    @Override
    public Optional<Product> getProductById(Long productId) {
        return loadPort.loadProductById(productId);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return loadPort.loadProducts(pageable);
    }

    @Override
    public Page<Product> getProductsByLike(Pageable pageable) {
        return loadPort.loadProductsByLike(pageable);
    }

    @Override
    public Page<Product> getProductsByCondition(Pageable pageable, String productTitle, Double minPrice, Double maxPrice) {
        return loadPort.loadProductsByCondition(pageable, productTitle, minPrice, maxPrice);
    }

    @Override
    public void addLike(Long productId, Long userId) {

    }

    @Override
    public void RemoveLike(Long productId, Long userId) {

    }
}
