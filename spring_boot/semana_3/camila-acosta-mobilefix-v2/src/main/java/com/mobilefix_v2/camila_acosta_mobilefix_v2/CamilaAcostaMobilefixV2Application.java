package com.mobilefix_v2.camila_acosta_mobilefix_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CamilaAcostaMobilefixV2Application {

	public static void main(String[] args) {
		SpringApplication.run(CamilaAcostaMobilefixV2Application.class, args);
	}

}
