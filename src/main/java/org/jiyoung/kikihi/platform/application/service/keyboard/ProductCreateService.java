package org.jiyoung.kikihi.platform.application.service.keyboard;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.ProductRequest;
import org.jiyoung.kikihi.platform.application.in.product.CreateProductUseCase;
import org.jiyoung.kikihi.platform.application.in.product.CreateTemporaryProductUseCase;
import org.jiyoung.kikihi.platform.application.out.keyboard.category.CategoryPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.OptionPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.SaveProductPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.product.LoadProductPort;
import org.jiyoung.kikihi.platform.application.out.keyboard.tag.TagPort;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;
import org.jiyoung.kikihi.platform.domain.keyboard.product.ProductOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCreateService implements CreateProductUseCase, CreateTemporaryProductUseCase {

    private final SaveProductPort savePort;
    private final LoadProductPort loadPort;

    // 의존성
    private final CategoryPort categoryPort;
    private final OptionPort optionPort;
    private final TagPort tagPort;

    @Override
    public Product createProduct(ProductRequest request) {

        // 기존 카테고리와 연결하기
        if(!categoryPort.existsCategory(request.getBasic().getCategoryCode())){
            throw new EntityNotFoundException("해당하는 카테고리가 존재하지 않습니다.");
        }

        // 상품 기본 정보 생성
        Product entity = Product.from(request);
        Product product = savePort.createProduct(entity);


        // 옵션 저장하기
        request.getOptions().forEach(option -> {
            ProductOption result = ProductOption.of(product.getId(), option.getColor(), option.getSwitchType(), option.getLayout(), option.isWireless(), option.getExtraPrice());
            optionPort.saveOption(result);
        });

        // 태그 설정하기


        return product;
    }

    @Override
    public void saveTemporaryProduct(ProductRequest request) {

        /*
            레디스 기반으로 임시저장 기능 구현
         */

    }

    @Override
    public Product getTemporaryProduct(Long userId) {

        /*
            레디스에서 임시저장한 기능 가져오기

         */

        return null;
    }
}
