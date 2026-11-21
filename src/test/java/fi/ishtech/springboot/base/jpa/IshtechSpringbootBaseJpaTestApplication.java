package fi.ishtech.springboot.base.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "fi.ishtech.springboot.base" })
public class IshtechSpringbootBaseJpaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IshtechSpringbootBaseJpaTestApplication.class, args);
	}

}