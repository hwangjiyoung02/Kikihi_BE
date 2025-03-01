package org.jiyoung.kikihi.domain.product.service;

import jakarta.servlet.http.HttpSession;
import org.jiyoung.kikihi.domain.product.dto.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductTemporarySaveService {
    // 각 항목을 세션에 임시로 저장하거나, 임시 테이블에 저장하는 로직을 구현합니다.

    private final HttpSession session;

    @Autowired
    public ProductTemporarySaveService(HttpSession session) {
        this.session = session;
    }

    // 각 항목을 세션에 임시로 저장하거나, 임시 테이블에 저장하는 로직을 구현합니다.
    public void saveTemporaryProduct(ProductTemporarySaveDto dto) {
        // 텍스트 정보 저장
        saveTextInfo(dto.getTextInfo());

        // 이미지 정보 저장
        saveImageInfo(dto.getImageInfo());

        // 옵션 정보 저장
        saveOptions(dto.getOptionInfo());

        // 태그 정보 저장
        saveTags(dto.getTagsInfo());

        // 세션에 데이터 임시 저장
        session.setAttribute("tempProductData", dto);
    }

    // 텍스트 정보 저장
    private void saveTextInfo(ProductTextDto textDto) {
        // 예: 세션에 텍스트 정보를 저장
        session.setAttribute("productCategory", textDto.getCategoryId());
        session.setAttribute("productName", textDto.getProductName());
        session.setAttribute("productTitle", textDto.getProductTitle());
        session.setAttribute("productManufacturer", textDto.getManufacturer());
    }

    // 이미지 정보 저장
    private void saveImageInfo(ProductImgDto imageDto) {
        // 예: 이미지 URL들을 세션에 저장
        session.setAttribute("productThumbnailImage", imageDto.getThumbnailImage());
        session.setAttribute("productMainImages", imageDto.getMainImages());
        session.setAttribute("productDescriptionImages", imageDto.getDescriptionImages());
    }

    // 옵션 정보 저장
    private void saveOptions(ProductOptionDto optionDto) {
        // 예: 옵션 정보를 세션에 저장
        session.setAttribute("productColor", optionDto.getColor());
        session.setAttribute("productSwitch", optionDto.getOption()); // 예시로 키스위치 옵션
    }

    // 태그 정보 저장
    private void saveTags(ProductTagDto tagsDto) {
        // 예: 태그 정보를 세션에 저장
        session.setAttribute("productTags", tagsDto.getTags());
    }
}
