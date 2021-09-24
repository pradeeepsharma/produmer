package org.reactive.programming.produmer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class ProdumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdumerApplication.class, args);
	}

}
