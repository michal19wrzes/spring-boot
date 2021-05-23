package pl.test1.spring1;

import pl.test1.spring1.appUser.*;
import pl.test1.spring1.registration.token.ConfirmationTokenRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {
		ConfirmationTokenRepository.class,
		UserRepository.class})
public class Spring1Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring1Application.class, args);
	}

}
