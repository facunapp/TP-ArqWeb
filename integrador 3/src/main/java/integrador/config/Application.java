package integrador.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "integrador")
@EntityScan("entities")
@EnableJpaRepositories("integrador.repository")
public class Application {

public static void main(String[] args) {
SpringApplication.run(Application.class, args);
}
}
