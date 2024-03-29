package mk.ukim.finki.webspring;

import mk.ukim.finki.webspring.model.Role;
import mk.ukim.finki.webspring.model.User;
import mk.ukim.finki.webspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableWebFlux
public class WebSpringApplication {

	@Autowired
	private UserService service;

	public static void main(String[] args) {
		SpringApplication.run(WebSpringApplication.class, args);
	}

}
