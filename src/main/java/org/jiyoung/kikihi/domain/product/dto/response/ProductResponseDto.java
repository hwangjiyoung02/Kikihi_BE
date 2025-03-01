package org.jiyoung.kikihi.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jiyoung.kikihi.domain.product.dto.request.ProductImgDto;
import org.jiyoung.kikihi.domain.product.dto.request.ProductOptionDto;
import org.jiyoung.kikihi.domain.product.dto.request.ProductTagDto;
import org.jiyoung.kikihi.domain.product.dto.request.ProductTextDto;
import org.jiyoung.kikihi.domain.product.entity.Product;
import org.jiyoung.kikihi.domain.product.entity.ProductImg;
import org.jiyoung.kikihi.domain.product.entity.ProductTag;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponseDto {
    @JsonProperty("textInfo")
    private ProductTextDto textInfo;

    @JsonProperty("imageInfo")
    private List<ProductImgDto> imageInfo;

    @JsonProperty("optionInfo")
    private List<ProductOptionDto> optionInfo;

    @JsonProperty("tagsInfo")
    private List<ProductTagDto> tagInfo;

    public ProductResponseDto(Object likedProduct) {
    }

    // 정적 메서드 from을 추가하여 4개의 DTO로부터 ProductResponseDto 객체를 생성
    public static ProductResponseDto from(ProductTextDto productTextDto, List<ProductImgDto> productImgDtos, List<ProductOptionDto> productOptionDtos, List<ProductTagDto> productTagDtos) {
        return ProductResponseDto.builder()
                .textInfo(productTextDto)
                .imageInfo(productImgDtos)
                .optionInfo(productOptionDtos)
                .tagInfo(productTagDtos)
                .build();
    }
    // 정적 메서드 from을 추가하여 4개의 DTO로부터 ProductResponseDto 객체를 생성 -> 단단히 잘못됐음
    public static ProductResponseDto fromEntity(Product product){
        return ProductResponseDto.builder()
                .textInfo(ProductTextDto.from(product))
                .imageInfo(Collections.singletonList(ProductImgDto.from((ProductImg) product.getProductImgs())))
                .optionInfo(Collections.singletonList(ProductOptionDto.from((Product) product.getProductOptions())))
                .tagInfo(Collections.singletonList(ProductTagDto.from((ProductTag) product.getProductTags())))
                .build();
    }

}
