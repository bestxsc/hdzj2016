package wl.hdzj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration.class})
@EnableTransactionManagement
public class Hdzj2016Application {
	public static void main(String[] args) {
		SpringApplication.run(Hdzj2016Application.class, args);
	}
}