package org.jiyoung.kikihi.elasticSearch;


import lombok.RequiredArgsConstructor;
import org.jiyoung.kikihi.domain.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElasticSearchService {
    private final ElasticSearchRepository elasticSearchRepository;

    //이름으로 조회
    public List<Product> searchByName(String name) {
        return elasticSearchRepository.findByProductTitle(name);
    }
}
