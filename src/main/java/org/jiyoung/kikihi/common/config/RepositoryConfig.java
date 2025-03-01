package org.jiyoung.kikihi.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "org.jiyoung.kikihi.domain.redis")  // Redis 리포지토리가 위치한 패키지 (필요시 활성화)
@EnableJpaRepositories(basePackages = "org.jiyoung.kikihi.domain")  // JPA 리포지토리가 위치한 패키지
@EnableElasticsearchRepositories(basePackages = "org.jiyoung.kikihi.elasticSearch")  // Elasticsearch 리포지토리가 위치한 패키지
public class RepositoryConfig {
    // 추가 설정이 필요한 경우 이곳에 작성
}
