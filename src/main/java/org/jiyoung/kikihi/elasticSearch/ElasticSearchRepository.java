package org.jiyoung.kikihi.elasticSearch;

import org.jiyoung.kikihi.domain.product.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElasticSearchRepository extends ElasticsearchRepository<Product, Long> {

    List<Product> findByProductTitle(String keyword);

}
