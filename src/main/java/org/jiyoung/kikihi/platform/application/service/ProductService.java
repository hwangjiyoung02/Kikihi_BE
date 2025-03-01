package org.jiyoung.kikihi.platform.application.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.jiyoung.kikihi.platform.adapter.in.web.dto.request.*;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.response.ProductResponseDto;
import org.jiyoung.kikihi.platform.adapter.out.jpa.product.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.sql.rowset.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final LikedProductRepository likedProductRepository;
    private final ProductTagRepository productTagRepository;

    // 상품 등록 관련
    public void saveFinalProduct(ProductTemporarySaveDto tempData) {
        try {
            // Tag entity 생성
            List<Tag> tags = tempData.getTagsInfo().getTags().stream()
                    .map(tagName -> Tag.builder()
                            .name(tagName)
                            .build())
                    .toList();

            // Product entity 생성
            Product product = Product.from(tempData.getTextInfo());
            // 연관관계 설정
            product.addProductOption(ProductOption.from(tempData.getOptionInfo()));
            product.addProductImg(ProductImg.from(tempData.getImageInfo()));
//            product.addProductTag(ProductTag.); -> 오 주여 모르겠어요
            product.addTags(tags);


            //카테고리는 원래 있는 것 중에 선택해야함
            Category category = categoryRepository.findById(tempData.getTextInfo().getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + tempData.getTextInfo().getCategoryId()));
            product.setCategory(category);
            productRepository.save(product);

            // 각각 repository를 사용해 저장 (Product → ProductImg → Tags 순으로 저장)
            productOptionRepository.saveAll(product.getProductOptions());
            productImgRepository.saveAll(product.getProductImgs());
            productTagRepository.saveAll(product.getProductTags());





    } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //  상품 삭제
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
        productRepository.delete(product);
    }

    // 상품 상세 조회
    // 1. Product로 관련된 모든 정보 join으로 가져온후 dto로 변환(이건 문제가 안됨)

    public ProductResponseDto getProductById(Long productId) {
     Product product=productRepository.findById(productId) .orElseThrow(()->new IllegalArgumentException("Product not found with id: "+productId));
    // product-> dto로 바꾸기
        ProductTextDto productTextDto= ProductTextDto.from(product);

    // ProductOption → ProductOptionDto 변환 -> 백퍼 문제생긴다 이건
        List<ProductOptionDto> optionDetailDtos = new ArrayList<>();
        for (ProductOption option : product.getProductOptions()) {
            ProductOptionDto from = ProductOptionDto.from(product);
            optionDetailDtos.add(from);
        }

    // Img -> dto로 변환-> s3하면 바뀔 듯
        List<ProductImgDto> imageDetailDtos = new ArrayList<>();
        for (ProductImg img : product.getProductImgs()) {
            ProductImgDto from = ProductImgDto.from(img);
            imageDetailDtos.add(from);
        }
    // tag -> tag dto로 변환
        List<ProductTagDto> tagDetailDtos = new ArrayList<>();
        for (ProductTag tag: product.getProductTags()){
            ProductTagDto from = ProductTagDto.from(tag);
            tagDetailDtos.add(from);
        }

     return  new ProductResponseDto(productTextDto,imageDetailDtos,optionDetailDtos,tagDetailDtos);
    }

    // 상품 목록 제공 (페이징, 정렬, 필터링)
    public Page<ProductResponseDto> getAllProducts(int page, int size, String sortBy, String direction, String productTitle, Double minPrice, Double maxPrice) {
        // 정렬 설정
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        // 필터링 조건을 Specification으로 처리
        Specification<Product> specification = buildSpecification(productTitle, minPrice, maxPrice);

        // Specification을 사용한 필터링, 페이징, 정렬
        Page<Product> productPage = productRepository.findAll(specification, pageable);

        // Page<Product>를 ProductResponseDto로 변환하여 반환
        return productPage.map(product -> ProductResponseDto.fromEntity(product));
    }
    // 동적 쿼리 생성 (필터링 조건을 Specification으로 처리)
    private Specification<Product> buildSpecification(String productTitle, Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (productTitle != null && !productTitle.isEmpty()) {
                predicates.add((Predicate) criteriaBuilder.like(root.get("productTitle"), "%" + productTitle + "%"));
            }

            if (minPrice != null) {
                predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add((Predicate) criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return (jakarta.persistence.criteria.Predicate) predicates;
        };
    }

    // 상품 좋아요 등록하기
    public void likeProduct(Long userId, Long productId) {
       // 어떤 사람이 어떤 상품에 좋아요 눌렀는지 등록
        LikedProductId likedProductId=new LikedProductId(userId,productId);

        // 이미 좋아요가 되어 있는지 확인
        if (likedProductRepository.findById(likedProductId).isPresent()) {
            throw new IllegalStateException("이미 좋아요를 누른 상품입니다.");
        }

        // 좋아요 저장
        LikedProduct likedProduct=LikedProduct.builder().id(likedProductId).likedAt(LocalDateTime.now()).build();
        likedProductRepository.save(likedProduct);
    }

    // 좋아요 취소하기
    @Transactional
    public void unlikeProduct(Long userId, Long productId) {
        LikedProductId likedProductId = new LikedProductId(userId, productId);

        // 좋아요 여부 확인 후 삭제
        if (likedProductRepository.findById(likedProductId).isEmpty()) {
            throw new IllegalStateException("좋아요를 누른 기록이 없습니다.");
        }

        likedProductRepository.deleteById(likedProductId);
    }

    // 마이페이지에서 좋아요 목록 가져오기
//    public List<ProductResponseDto> getLikedProducts(Long userId) {
//        List<Product> likedProducts = likedProductRepository.findById(userId);
//        return likedProducts.stream()
//        .map(ProductResponseDto::fromEntity)
//        .toList();
//
//    }


}
