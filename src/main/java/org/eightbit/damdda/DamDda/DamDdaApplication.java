package org.eightbit.damdda.DamDda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DamDdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DamDdaApplication.class, args);
	}

}
