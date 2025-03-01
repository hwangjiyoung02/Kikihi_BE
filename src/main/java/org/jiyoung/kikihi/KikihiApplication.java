package org.jiyoung.kikihi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableCaching
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "org.jiyoung.kikihi.platform.adapter.out.jpa")  // JPA 리포지토리가 위치한 패키지
@EnableRedisRepositories(basePackages = "org.jiyoung.kikihi.platform.adapter.out.redis")  // Redis 리포지토리가 위치한 패키지
//@EnableElasticsearchRepositories(basePackages = "org.jiyoung.kikihi.platform.adapter.out.aws.elasticSearch")
@SpringBootApplication
public class KikihiApplication {
	public static void main(String[] args) {
		SpringApplication.run(KikihiApplication.class, args);
	}
}
