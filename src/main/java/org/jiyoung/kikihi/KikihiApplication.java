package org.jiyoung.kikihi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "org.jiyoung.kikihi.member.repository")
@SpringBootApplication(scanBasePackages = "org.jiyoung.kikihi")
public class KikihiApplication {
	public static void main(String[] args) {
		SpringApplication.run(KikihiApplication.class, args);
	}
}
