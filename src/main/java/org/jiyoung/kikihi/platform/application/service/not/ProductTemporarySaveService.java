package org.jiyoung.kikihi.platform.application.service.not;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product.*;
import org.jiyoung.kikihi.platform.application.in.product.CreateTemporaryProductUseCase;
import org.jiyoung.kikihi.platform.domain.keyboard.product.Product;

@RequiredArgsConstructor
public class ProductTemporarySaveService implements CreateTemporaryProductUseCase {
    // 각 항목을 세션에 임시로 저장하거나, 임시 테이블에 저장하는 로직을 구현합니다.

    private final HttpSession session;

    // 각 항목을 세션에 임시로 저장하거나, 임시 테이블에 저장하는 로직을 구현합니다.
    public void saveTemporaryProduct(ProductRequest request) {
        // 텍스트 정보 저장
        saveTextInfo(request.getBasic());

        // 옵션 정보 저장
        request.getOptions().forEach(this::saveOptions);

        // 태그 정보 저장
        request.getOptions().forEach(this::saveOptions);

        // 세션에 데이터 임시 저장
        session.setAttribute("tempProductData", request);
    }

    @Override
    public Product getTemporaryProduct(Long userId) {
        return null;
    }

    // 텍스트 정보 저장
    private void saveTextInfo(ProductBasicRequest request) {
        // 예: 세션에 텍스트 정보를 저장
        session.setAttribute("productCategory", request.getCategoryCode());
        session.setAttribute("productName", request.getProductName());
        session.setAttribute("productTitle", request.getProductTitle());
        session.setAttribute("productManufacturer", request.getManufacturer());
    }

    // 옵션 정보 저장
    private void saveOptions(ProductOptionRequest request) {
        // 예: 옵션 정보를 세션에 저장
        session.setAttribute("productColor", request.getColor());
        session.setAttribute("productSwitch", request.getSwitchType()); // 예시로 키스위치 옵션
        session.setAttribute("productLayout", request.getLayout());
        session.setAttribute("productPrice", request.getExtraPrice());
    }

    // 태그 정보 저장
    private void saveTags(ProductTagRequest request) {
        // 예: 태그 정보를 세션에 저장
        session.setAttribute("productTags", request.getCode());
    }
}
