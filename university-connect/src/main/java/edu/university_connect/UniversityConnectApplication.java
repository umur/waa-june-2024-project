package edu.university_connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UniversityConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityConnectApplication.class, args);
	}

}
