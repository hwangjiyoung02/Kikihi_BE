package org.jiyoung.kikihi.platform.application.service.keyboard;

import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.application.in.product.GetProductOptionUseCase;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.OptionPort;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductOptionService implements GetProductOptionUseCase {

    private final OptionPort optionPort;

    @Override
    public Optional<ProductOption> getProductOption(Long optionId) {
        return optionPort.loadOption(optionId);
    }

    @Override
    public List<ProductOption> getProductOptions(Long productId) {
        return optionPort.loadOptionByProductId(productId);
    }
}
