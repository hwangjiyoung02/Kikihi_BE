package org.jiyoung.kikihi.domain.product.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.jiyoung.kikihi.domain.product.dto.request.ProductOptionDto;
import org.jiyoung.kikihi.domain.product.dto.request.ProductTemporarySaveDto;
import org.jiyoung.kikihi.domain.product.dto.request.ProductTextDto;
import org.jiyoung.kikihi.domain.product.dto.response.ProductResponseDto;
import org.jiyoung.kikihi.domain.product.entity.*;
import org.jiyoung.kikihi.domain.product.repository.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductImgRepository productImgRepository;
    private final TagRepository tagRepository;


    // 1. 상품 등록 관련
    public void saveFinalProduct(ProductTemporarySaveDto tempData) {
        try {
            // Product entity 생성
            Product product = Product.builder()
                    .productName(tempData.getTextInfo().getProductName())
                    .manufacturer(tempData.getTextInfo().getManufacturer())
                    .productTitle(tempData.getTextInfo().getProductTitle())
                    .productPrice(tempData.getTextInfo().getProductPrice())
                    .brand(tempData.getTextInfo().getBrand())
                    .build();

            //카테고리는 원래 있는 것 중에 선택해야함
            Category category = categoryRepository.findById(tempData.getTextInfo().getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + tempData.getTextInfo().getCategoryId()));
            product.setCategory(category);

            //옵션
            ProductOption productOption=ProductOption.builder()
                    .product(product)
                    .color(tempData.getOptionInfo().getColor())
                    .option(tempData.getOptionInfo().getOption())
                    .build();

            // ProductImg entity 생성
            ProductImg productImg = ProductImg.builder()
                    .product(product)
                    .thumbnailImg(tempData.getImageInfo().getThumbnailImage())
                    .mainImgs(tempData.getImageInfo().getMainImages())
                    .descriptionImgs(tempData.getImageInfo().getDescriptionImages())
                    .descriptionHtml(tempData.getImageInfo().getDescriptionHtml())
                    .build();

            // Tag entity 생성
            List<Tag> tags = tempData.getTagsInfo().getTags().stream()
                    .map(tagName -> Tag.builder()
                            .name(tagName)
                            .build())
                    .toList();

            // 각각 repository를 사용해 저장 (Product → ProductImg → Tags 순으로 저장)
            productRepository.save(product);
            productOptionRepository.save(productOption);
            productImgRepository.save(productImg);
            tagRepository.saveAll(tags);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 2. 상품 삭제
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
        productRepository.delete(product);
    }

    // 상품 상세 조회
//    public ProductResponseDto getProductById(Long productId) {
//     Product product=productRepository.findById(productId) .orElseThrow(()->new IllegalArgumentException("Product not found with id: "+productId));
//    // product-> dto로 바꾸기
//        ProductTextDto productTextDto= ProductTextDto.builder()
//                .categoryId(product.getCategory().getCategoryId())
//                .productName(product.getProductName())
//                .brand(product.getBrand())
//                .productPrice(product.getProductPrice())
//                .manufacturer(product.getManufacturer())
//                .build();
//
//        // ProductOption → ProductOptionDto 변환
//        List<ProductOptionDto> optionDetailDtos = product.getProductOptions().stream()
//                .map(option -> ProductOptionDto.builder()
//                        .color(option.getColor())
//                        .option(option.getOption())
//                        .build())
//                .toList();
//
//        // tag -> tag dto로 변환
//
//     return  new ProductResponseDto();
//    }



    // 상품 목록 제공 (페이징, 정렬, 필터링)
//    public Page<Product> getProducts(int page, int size, String sortBy, String direction) {
//        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//        return productRepository.findAll(pageable);
//    }
//
//    // 상품 상세 조회
//    public Product getProductDetails(Long productId) {
//        return productRepository.findById(productId)
//                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
//    }



    // 좋아요 목록 가져오기 (예시로 Product entity에 likeCount가 있다고 가정)
//    public List<Product> getLikedProducts() {
//        return productRepository.findTopLikedProducts(); // 예시로 `findTopLikedProducts` 메서드를 호출
//    }
}
