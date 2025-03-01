package org.jiyoung.kikihi.platform.adapter.out.aws.elasticSearch;


import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.platform.adapter.out.jpa.product.Product;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ElasticSearchService {
    private final ElasticSearchRepository elasticSearchRepository;


    public void saveProduct(Product product) {
        elasticSearchRepository.save(product);  // 이 부분이 Elasticsearch에 데이터를 저장
    }
    // 1. 기본적인 상품 제목 검색
    public List<Product> searchByKeyword(String productTitle, Limit limit) {
        // 기본적인 제목 검색
        return elasticSearchRepository.findByProductTitleContainsIgnoreCase(productTitle, limit);
    }

    // 2. 와일드카드 쿼리를 활용한 상품 제목 검색 (페이징 처리)
    public Page<Product> searchByKeywordWithWildcard(String productTitle, Pageable pageable) {
        // 와일드카드를 활용한 제목 검색 (페이징 처리)
        return elasticSearchRepository.findByProductTitleContainsIgnoreCase(productTitle, pageable);
    }
}
