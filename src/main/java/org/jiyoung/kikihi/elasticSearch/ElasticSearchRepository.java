package org.jiyoung.kikihi.elasticSearch;

import org.jiyoung.kikihi.domain.product.entity.Product;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElasticSearchRepository extends ElasticsearchRepository<Product, Long> {
    Optional<Product> findById(Long id);
    // `Limit`을 적용한 메소드 (예: 한 번에 조회할 최대 상품 수 제한)
    List<Product> findByProductTitleContainsIgnoreCase(String productTitle, Limit limit);


    @Query("{\"bool\": { \"must\": [ \n" +
            "    {\"wildcard\": {\"productTitle\": \"*?0*\"}}]}}")
    Page<Product> findByProductTitleContainsIgnoreCase(String productTitle, Pageable pageable);


}
