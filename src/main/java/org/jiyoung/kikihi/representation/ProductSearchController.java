package org.jiyoung.kikihi.representation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jiyoung.kikihi.domain.common.response.ApiResponse;
import org.jiyoung.kikihi.domain.common.response.CustomException;
import org.jiyoung.kikihi.elasticSearch.ElasticSearchRepository;
import org.jiyoung.kikihi.domain.product.entity.Product;
import org.jiyoung.kikihi.elasticSearch.ElasticSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.jiyoung.kikihi.domain.common.response.ErrorCode.INTERNAL_SERVER_ERROR;

@Slf4j
@RestController
@RequestMapping("/api/v1/productSearch")
@RequiredArgsConstructor
public class ProductSearchController {

    private final ElasticSearchRepository elasticSearchRepository;
    private final ElasticSearchService elasticSearchService;

    @GetMapping
    public ApiResponse<List<Product>> search(@RequestParam String keyword) {
        try{
        List<Product> products = elasticSearchService.searchByName(keyword);
        return ApiResponse.ok(products);}
        catch (Exception e){
            return ApiResponse.fail(new CustomException(INTERNAL_SERVER_ERROR));
        }
    }
}
